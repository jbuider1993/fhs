package com.fhs.ucenter.service.impl;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.Logger;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.config.EConfig;
import com.fhs.core.db.DataSource;
import com.fhs.core.exception.ParamException;
import com.fhs.redis.service.RedisCacheService;
import com.fhs.ucenter.bean.UcenterFrontUser;
import com.fhs.ucenter.bean.UcenterFrontUserBind;
import com.fhs.ucenter.service.LoginService;
import com.fhs.ucenter.service.UcenterFrontUserBindService;
import com.fhs.ucenter.service.UcenterFrontUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 用户登录服务
 * @Version: 1.0
 * @Author: jackwong
 */
@Service
@DataSource("base_business")
public class LoginServiceImpl implements LoginService
{

    private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

    /**
     * client token和access token的redis key
     */
    private static final String CLIENT_TOKEN_ACCESS_TOKEN_KEY = "ucenter:client_token_access_token:";

    /**
     * ACCESS_TOKEN 和 userid的rediskey
     */
    private static final String ACCESS_TOKEN_USER_KEY = "ucenter:access_token_user:";

    /**
     * ACCESS_TOKEN对应的用户是否的vip
     */
    private static final String ACCESS_TOKEN_USER_IS_VIP = "ucenter:access_token_user_is_vip:";

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




    /**
     * 根据userid登录
     *
     * @param userId 用户id
     * @return accesstoken
     */
    public String login(String userId)
    {
        UcenterFrontUser user = userService.findBeanById(userId);
        if (CheckUtils.isNullOrEmpty(userId))
        {
            throw new ParamException("用户id不能为空");
        }
        if (user == null)
        {
            throw new ParamException("用户id无效:" + userId);
        }
        String accessToken = StringUtil.getUUID();
        redisCacheService.addStr(ACCESS_TOKEN_USER_KEY + accessToken, userId);
        redisCacheService.expire(ACCESS_TOKEN_USER_KEY + accessToken,
                ConverterUtils.toInt(EConfig.getOtherConfigPropertiesValue("access_token_time_out_second")));
        HttpServletRequest request =
            ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        if (request != null)
        {
            request.getSession().setAttribute("accessToken", accessToken);
        }
        LOGGER.info("用户登录成功:" + accessToken);
        return accessToken;
    }

    @Override
    public String addBindAndUser(UcenterFrontUser user, String openId, int openIdType) {
        user.setUserId(StringUtil.getUUID());
        userService.insertJpa(user);
        UcenterFrontUserBind bind = UcenterFrontUserBind.builder().id(StringUtil.getUUID()).authOpenid(openId)
                .authOpenidType(openIdType).userId(user.getUserId()).build();
        userBindService.insertJpa(bind);
        return user.getUserId();
    }

    @Override
    public String getUserIdByAccessToken(String accessToken)
    {
        String userId = redisCacheService.getStr(ACCESS_TOKEN_USER_KEY + accessToken);
        return userId;
    }


}
