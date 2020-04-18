package com.fhs.module.base.filter;


import com.fhs.basics.dox.UcenterMsUserDO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.JsonUtils;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.result.PubResult;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * api鉴权
 */
@WebFilter(urlPatterns = {"*"}, filterName = "aVueFilter", asyncSupported = true)
public class AVueFilter implements Filter {

    @Autowired
    private RedisCacheService<UcenterMsUserDO> redisCacheService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getHeader("Authorization") != null) {
            String token = request.getHeader("Authorization");
            UcenterMsUserDO sysUser = redisCacheService.get("shiro:user:" + token);
            if(sysUser==null){
                JsonUtils.outJson((HttpServletResponse) servletResponse, PubResult.NO_PERMISSION.asResult().asJson());
                return;
            }
            request.getSession().setAttribute(Constant.SESSION_USER,sysUser);
        }
        filterChain.doFilter(request,servletResponse);
    }
}
