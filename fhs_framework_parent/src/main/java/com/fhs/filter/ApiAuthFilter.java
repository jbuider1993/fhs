package com.fhs.filter;

import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.HttpUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.result.PubResult;
import com.fhs.feignConfig.FeignConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * api鉴权
 */
@ServletComponentScan
@WebFilter(urlPatterns = {"/api/*"},filterName = "apiAuthFilter" ,asyncSupported = true)
public class ApiAuthFilter implements Filter {

    String apiToken = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(apiToken==null)
        {
            apiToken = SpringContextUtil.getBeanByName(FeignConfiguration.class).getApiToken();
        }
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        String reqApiToken = request.getHeader("apiToken");
        if(CheckUtils.isNullOrEmpty(reqApiToken) || !reqApiToken.equals(apiToken))
        {
            JsonUtils.outJson((HttpServletResponse)servletResponse, PubResult.NO_PERMISSION.asResult().asJson());
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
