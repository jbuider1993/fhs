package com.fhs.front.interfaces;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 指的是通过外部openid来进行登录的第三方集成，比如微信公众号，支付宝服务号
 * 此接口需要跳转到相关url，在跳转回来，从url获取code/其他 要素来认证用户
 */
public interface FhsOauth302 {
    /**
     * 哪种浏览器UA需要使用此类型
     * @return
     */
   String getUA();

    /**
     * 拼接302的登录url
     * @param request
     * @param response
     */
   String getLoginUrl(HttpServletRequest request, HttpServletResponse response);

    /**
     * 完成登录
     * @param request
     * @param response
     */
   void complateLogin(HttpServletRequest request, HttpServletResponse response);
}
