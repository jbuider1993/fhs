package com.fhs.pub.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局路径filter
 * 
 * @author jianbo.qin
 *
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*",filterName = "SimpleCORSFilter")
public class SimpleCORSFilter implements Filter
{

    /**
     * Default constructor.
     */
    public SimpleCORSFilter()
    {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy()
    {
        // TODO Auto-generated method stub
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain)
        throws IOException, ServletException
    {
        HttpServletRequest httpRequest = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;

        // 设置允许跨域访问的域，*表示支持所有的来源
        response.setHeader("Access-Control-Allow-Origin", "*");
        // 设置允许跨域访问的方法
        response.setHeader("Access-Control-Allow-Methods",
                "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "36000");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        response.setHeader("Access-Control-Allow-Headers","X_Requested_With,jackToken");

        filterChain.doFilter(req, rep);
    }
    
    /**
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig)
        throws ServletException
    {

    }
}
