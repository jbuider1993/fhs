package com.fhs.front.controller;

import com.fhs.common.utils.*;
import com.fhs.core.exception.ParamException;
import com.fhs.front.service.LoginService;
import com.fhs.logger.Logger;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 前段用户登录注册 by jackwong
 */
@RestController
@RequestMapping("/webApi/front")
public class FrontUserLoginWebApiAction {
    //日志
    private static final Logger LOGGER = Logger.getLogger(FrontUserLoginWebApiAction.class);


    /**
     * 登录服务
     */
    @Autowired
    private LoginService loginService;


    /**
     * 用户登录
     * 自动识别登录方式
     *
     * @param request  请求
     * @param response 响应
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ApiOperation(value = "用户登录")
    public void login(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession();
        String callBack = request.getParameter("callBack");
        if (CheckUtils.isNullOrEmpty(callBack)) {
            throw new ParamException("callBack 不可以为空");
        }
        session.setAttribute("callBack", callBack);
        String accessToken = ConverterUtils.toString(session.getAttribute("accessToken"));
        //通过accessToken获取用户id
        String userId = loginService.getUserIdByAccessToken(accessToken);
        if (CheckUtils.isNullOrEmpty(userId)) {
            session.removeAttribute("accessToken");
        } else {
            response.sendRedirect(loginService.checkUrl(callBack, accessToken));
            return;
        }
        //截下来处理各种 SSO请求
        String userAgent = ConverterUtils.toString(request.getHeader("user-agent"));
        String url = loginService.getOauth302Impl(userAgent).getLoginUrl(request, response);
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            LOGGER.error("302错误:", e);
        }
    }

    /**
     * 完成登录
     *
     * @param request
     * @param response
     */
    @RequestMapping("complateLogin")
    public void complateLogin(HttpServletRequest request, HttpServletResponse response) {
        //截下来处理各种 SSO请求
        String userAgent = ConverterUtils.toString(request.getHeader("user-agent"));
        loginService.getOauth302Impl(userAgent).complateLogin(request, response);
    }

}

