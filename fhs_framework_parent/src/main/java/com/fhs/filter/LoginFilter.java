package com.fhs.filter;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.config.EConfig;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * by wanglei
 * 后台用户登录filter
 */
@ServletComponentScan
@WebFilter(urlPatterns = {"*"},filterName = "loginFilter" ,asyncSupported = true)
public class LoginFilter implements Filter{

    private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        String basePath = EConfig.getPathPropertiesValue("basePath");
        String uri = request.getRequestURI();
        //访问根目录跳转到首页
        if(CheckUtils.isNullOrEmpty(uri) || "/".equals(uri)   )
        {
            response.sendRedirect(basePath + "ms/index");
        }
        //如果当前用户为空并且访问受保护的资源
        else if(((uri.startsWith("/page/ms") || uri.contains("/b/page-ms") ||  uri.contains("/ms/")) && request.getSession().getAttribute(Constant.SESSION_USER) == null ))
        {
            String extendsParam = CheckUtils.isNullOrEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString();
            request.getSession().setAttribute("serviceURL", request.getRequestURL().toString() + extendsParam);
            response.sendRedirect(basePath + "ms/index");
        }
        else
        {
            chain.doFilter (req, res);
        }

    }

    @Override
    public void destroy() {

    }



}
