package com.fhs.front.service.impl;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.CreateCache;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.config.EConfig;
import com.fhs.core.db.ds.DataSource;
import com.fhs.core.exception.ParamException;
import com.fhs.front.dox.UcenterFrontUserBindDO;
import com.fhs.front.dox.UcenterFrontUserDO;
import com.fhs.front.interfaces.FhsOauth302;
import com.fhs.front.service.LoginService;
import com.fhs.front.service.UcenterFrontUserBindService;
import com.fhs.front.service.UcenterFrontUserService;
import com.fhs.front.vo.UcenterFrontUserVO;
import com.fhs.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 用户登录服务
 * @Version: 1.0
 * @Author: jackwong
 */
@Service
@DataSource("base_business")
public class LoginServiceImpl implements LoginService {

    private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

    /**
     * 缓存 默认时间：半个小时
     */
    @CreateCache(expire = 1800, name = "frontuser:token", cacheType = CacheType.BOTH)
    private Cache<String, String> frontUserTokenMap;

    /**
     * key ua value 对应的服务实现类
     */
    private Map<String, FhsOauth302> fhsOauth302Map = new HashMap<>();

    /**
     * ACCESS_TOKEN 和 userid的rediskey
     */
    private static final String ACCESS_TOKEN_USER_KEY = "basics:access_token_user:";

    /**
     * ACCESS_TOKEN对应的用户是否的vip
     */
    private static final String ACCESS_TOKEN_USER_IS_VIP = "basics:access_token_user_is_vip:";

    /**
     * redis缓存服务
     */
    @Autowired
    private RedisCacheService<String> redisCacheService;

    /**
     * 用户服务
     */
    @Autowired
    private UcenterFrontUserService userService;

    /**
     * 用户绑定服务
     */
    @Autowired
    private UcenterFrontUserBindService userBindService;

    @Autowired
    private UcenterFrontUserService frontUserService;

    private Integer timeOutSecond;

    /**
     * 根据userid登录
     *
     * @param userId 用户id
     * @return accesstoken
     */
    public String login(String userId) {
        UcenterFrontUserVO user = userService.selectById(userId);
        if (CheckUtils.isNullOrEmpty(userId)) {
            throw new ParamException("用户id不能为空");
        }
        if (user == null) {
            throw new ParamException("用户id无效:" + userId);
        }
        if (timeOutSecond == null) {
            timeOutSecond = ConverterUtils.toInt(EConfig.getOtherConfigPropertiesValue("access_token_time_out_second"));
            LOGGER.info("读取access_token_time_out_second:" + timeOutSecond);
        }
        String accessToken = StringUtil.getUUID();
        redisCacheService.addStr(ACCESS_TOKEN_USER_KEY + accessToken, userId);
        frontUserTokenMap.put(accessToken, userId);
        LOGGER.info("添加accessToken:" + accessToken);
        redisCacheService.expire(ACCESS_TOKEN_USER_KEY + accessToken,
                timeOutSecond);
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (request != null) {
            request.getSession().setAttribute("accessToken", accessToken);
        }
        LOGGER.info("用户登录成功:" + accessToken);
        return accessToken;
    }

    @Override
    public String addBindAndUser(UcenterFrontUserDO user, String openId, int openIdType) {
        user.setUserId(StringUtil.getUUID());
        user.preInsert(null);
        userService.insertSelective(user);
        UcenterFrontUserBindDO bind = UcenterFrontUserBindDO.builder().id(StringUtil.getUUID()).authOpenid(openId)
                .authOpenidType(openIdType).userId(user.getUserId()).build();
        bind.preInsert(null);
        userBindService.insertSelective(bind);
        return user.getUserId();
    }

    @Override
    public Map<String, FhsOauth302> getOauthServiceMap() {
        return this.fhsOauth302Map;
    }

    @Override
    public FhsOauth302 getOauth302Impl(String userAgent) {
        if (userAgent == null) {
            throw new ParamException("暂时不支持此种浏览器访问,UA:" + userAgent);
        }
        userAgent = userAgent.toLowerCase();
        //看看谁去处理
        for (String ua : fhsOauth302Map.keySet()) {
            if (userAgent.contains(ua)) {
                return fhsOauth302Map.get(ua);
            }
        }
        throw new ParamException("暂时不支持此种浏览器访问,UA:" + userAgent);
    }



    @Override
    public void loginAndRedirect(HttpServletRequest request, HttpServletResponse response, String userId) {
        UcenterFrontUserVO user = frontUserService.selectById(userId);
        if (user == null) {
            throw new ParamException("用户信息丢失:" + userId);
        }
        if (user.getIsEnable() != Constant.INT_TRUE) {
            throw new ParamException("用户被禁用");
        }
        String accessToken = this.login(userId);
        request.getSession().setAttribute("accessToken", accessToken);
        String callback = request.getSession().getAttribute("callBack").toString();
        try {
            response.sendRedirect(checkUrl(callback, accessToken));
        } catch (IOException e) {
            LOGGER.error("302到登录前页面错误", e);
        }
    }

    /**
     * 对URL参数进行过滤处理.
     *
     * @param callback    回调地址
     * @param accessToken token信息
     * @return 解码后的回调地址
     */
    public String checkUrl(String callback, String accessToken)
            throws  UnsupportedEncodingException{
        callback = new String(callback.getBytes(), "utf-8");
        callback = java.net.URLDecoder.decode(callback, "utf-8");
        if (!callback.startsWith("http")) {
            throw new ParamException("callback地址格式有误!");
        }
        if (callback.contains("?")) {
            if (callback.endsWith("?")) {
                return callback + "accessToken=" + accessToken;
            } else {
                return callback + "&accessToken=" + accessToken;
            }
        } else {
            return callback + "?accessToken=" + accessToken;
        }
    }

    @Override
    public String getUserIdByAccessToken(String accessToken) {
        if (CheckUtils.isNotEmpty(frontUserTokenMap.get(accessToken))) {
            return frontUserTokenMap.get(accessToken);
        }
        return redisCacheService.getStr(ACCESS_TOKEN_USER_KEY + accessToken);
    }


}
