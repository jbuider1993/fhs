package com.fhs.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户请求地址拦截器
 *
 * @Filename: IndexInterceptor.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class IndexInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexInterceptor.class);


    /**
     * 拦截非/ms/index用户请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        /*if(!uri.startsWith("/ms/index") && CheckUtils.isNullOrEmpty(request.getSession().getAttribute("sessionUser"))){
            String extendsParam = CheckUtils.isNullOrEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString();
            request.getSession().setAttribute("serviceURL", request.getRequestURL().toString() + extendsParam);
            response.sendRedirect(EConfig.getPathPropertiesValue("basePath") + "/ms/index?serviceUrl=" + URLEncoder.encode(request.getRequestURL().toString() + extendsParam,"UTF-8"));
            return false;
        }else {
            return true;
        }*/
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }

}
