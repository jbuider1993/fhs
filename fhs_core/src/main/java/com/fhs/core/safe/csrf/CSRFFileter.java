package com.fhs.core.safe.csrf;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 跨域的请求攻击 过滤器
 * CSRF 详见 https://baike.baidu.com/item/CSRF/2735433?fr=aladdin
 * @Filename: CsfrFileter.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwang
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 *   陕西小伙伴网络科技有限公司 Copyright (c) 2017 All Rights Reserved.
 *
 */
public class CSRFFileter implements Filter
{



    @Override
    public void destroy()
    {

    }

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
        throws IOException, ServletException
    {
        HttpServletRequest request = (HttpServletRequest)arg0;
        String requestURI = request.getRequestURI();
/*
        //如果是高危操作，则需要判断是否存在CSFR跨域攻击
        if ((requestURI.contains("/add") || requestURI.contains("/del") || requestURI.contains("/update") || requestURI.contains("/save"))
        && (!requestURI.contains(".jsp")))
        {
            String token = request.getHeader("jackToken");
            if (CheckUtils.isNullOrEmpty(token))
            {
                token = request.getParameter("jackToken");
            }
            if (CheckUtils.isNullOrEmpty(token))
            {
                throw new BusinessException("Cross-site request forgery");
            }
            // session的和传的不同
            if (!token.equals(request.getSession().getAttribute("jackToken")))
            {
                //如果传的和session的不同，如果cookie和传的相同也给他过
                String cookieJackToken = CookieUtil.readCookie("jackToken",request);
                // 如果传的不对，cookie里面还没有，就返回跨域错误
                if(CheckUtils.isNullOrEmpty(cookieJackToken))
                {
                    throw new BusinessException("Cross-site request forgery c");
                }
                //如果传的不对，但是传的值和cookie的值相同，是允许的
                if(token.equals(cookieJackToken))
                {
                    request.getSession().setAttribute("jackToken",cookieJackToken);
                }else//如果传的和session中的不同，也和cookie中的不同，那么认为这个请求是被攻击的
                {
                    throw new ParamException("Cross-site request forgery token time out");
                }

            }
        }*/
        arg2.doFilter(arg0, arg1);
    }


    @Override
    public void init(FilterConfig arg0)
        throws ServletException
    {

    }
}
