package com.fhs.front.wx;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.ParamException;
import com.fhs.front.dox.UcenterFrontUserDO;
import com.fhs.front.interfaces.FhsOauth302;
import com.fhs.front.service.LoginService;
import com.fhs.front.service.UcenterFrontUserBindService;
import com.fhs.front.vo.UcenterFrontUserBindVO;
import com.fhs.front.vo.UcenterFrontUserVO;
import com.fhs.front.wx.tools.WxTools;
import com.fhs.logger.Logger;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 微信mp接入
 */
@Component
public class WxMpOauth implements FhsOauth302, InitializingBean {

    private static final Logger LOGGER = Logger.getLogger(WxMpOauth.class);

    @Autowired
    private WxTools wxTools;

    @Autowired
    private UcenterFrontUserBindService frontUserBindService;

    @Autowired
    private LoginService loginService;

    @Override
    public String getUA() {
        return "micromessenger";
    }

    @Override
    public String getLoginUrl(HttpServletRequest request, HttpServletResponse response) {
        // 存在openId
        if (request.getSession().getAttribute("openId") != null) {
            try {
                handelWXLoginWidthOpenId(request, response, (String) request.getSession().getAttribute("openId"));
            } catch (IOException e) {
                LOGGER.error("使用微信openid登录失败:", e);
                throw new ParamException("使用微信openid登录失败,请重试");
            }
        } else {
            // 不存在的时候 构建地址 让用户去请求openId
            String url = EConfig.getPathPropertiesValue("basePath") + "/webApi/front/complateLogin";
            String wxUrl = wxTools.getWxMpService().oauth2buildAuthorizationUrl(url,
                    "snsapi_base",
                    null);
            return wxUrl;
        }
        return null;
    }


    @Override
    public void complateLogin(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        WxMpService wxMpService = wxTools.getWxMpService();
        try {
            WxMpOAuth2AccessToken wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(request.getParameter("code"));
            String openId = wxMpOAuth2AccessToken.getOpenId();
            session.setAttribute("openId", openId);
            handelWXLoginWidthOpenId(request, response, openId);
        } catch (IOException e) {
            LOGGER.error("使用微信openid登录失败:", e);
            throw new ParamException("使用微信openid登录失败,请重试");
        } catch (WxErrorException e) {
            LOGGER.error("获取微信openid失败:", e);
            throw new ParamException("获取微信openid失败,请重试");
        }
    }


    /**
     * 通过userId登录 如果没有绑定手机,要求他去绑定.
     *
     * @param request  请求
     * @param response 响应
     */
    private void handelWXLoginWidthOpenId(HttpServletRequest request, HttpServletResponse response, String openId)
            throws IOException {
        HttpSession session = request.getSession();
        UcenterFrontUserBindVO bind = frontUserBindService.selectBean(UcenterFrontUserBindVO.builder().authOpenid(openId).authOpenidType(UcenterFrontUserBindService.OPENID_TYPE_WXMP)
                .build());
        String userId = null;
        if (bind == null) {
            try {
                WxMpUser mpUser = wxTools.getWxMpService().getUserService().userInfo(openId);
                UcenterFrontUserDO user = UcenterFrontUserVO.builder().userId(StringUtil.getUUID())
                        .nickName(mpUser.getNickname()).provinceId(mpUser.getProvince()).cityId(mpUser.getCity()).isEnable(Constant.INT_TRUE)
                        .sex(ConverterUtils.toString(mpUser.getSex())).language(mpUser.getLanguage()).imagePath(mpUser.getHeadImgUrl()).build();
                userId = loginService.addBindAndUser(user, openId, UcenterFrontUserBindService.OPENID_TYPE_WXMP);
            } catch (WxErrorException e) {
                LOGGER.error("根据openid获取用户信息错误:", e);
            }
        } else {
            userId = bind.getUserId();
        }
        loginService.loginAndRedirect(request, response, userId);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.loginService.getOauthServiceMap().put(this.getUA(),this);
    }
}
