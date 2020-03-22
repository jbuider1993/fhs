package com.fhs.module.base.filter;

import com.fhs.basics.vo.SysUserVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.core.config.EConfig;
import com.fhs.logger.Logger;
import com.mybatis.jpa.context.MultiTenancyContext;
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
@WebFilter(urlPatterns = {"*"},filterName = "zLoginFilter" ,asyncSupported = true)
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
        MultiTenancyContext.setProviderId(null);
        //访问根目录跳转到首页
        if(CheckUtils.isNullOrEmpty(uri) || "/".equals(uri)   )
        {
            response.sendRedirect(basePath + "ms/index");
            return;
        }
        //如果当前用户为空并且访问受保护的资源
        else if(((uri.startsWith("/page/ms") || uri.contains("/b/page-ms") ||  uri.contains("/ms/")) && (!uri.contains("/ms/index"))))
        {
            if(request.getSession().getAttribute(Constant.SESSION_USER) == null)
            {
                String extendsParam = CheckUtils.isNullOrEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString();
                request.getSession().setAttribute("serviceURL", request.getRequestURL().toString() + extendsParam);
                response.sendRedirect(basePath + "ms/index");
            }
            else
            {
                SysUserVO user = (SysUserVO)request.getSession().getAttribute(Constant.SESSION_USER);
                MultiTenancyContext.setProviderId(user.getGroupCode());
                chain.doFilter (req, res);
            }
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
