package com.fhs.front.wx.filter;

import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.FileUtils;
import com.fhs.core.config.EConfig;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信txt验证
 */
@WebFilter(urlPatterns = {"*"},filterName = "mpVerifyFilter" ,asyncSupported = true)
public class MPVerifyFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = ConverterUtils.toString(request.getRequestURI());
        if(uri.contains("MP_verify"))
        {
            String fileName = uri.substring(uri.indexOf("MP_verify"));
            FileUtils.download( EConfig.getPathPropertiesValue("mp_verify_file_path") + "/" + fileName,(HttpServletResponse) res,"verify.txt");
            return;
        }
        else
        {
            chain.doFilter(req,res);
        }
    }

    @Override
    public void destroy() {

    }



}

