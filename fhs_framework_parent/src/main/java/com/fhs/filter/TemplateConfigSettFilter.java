package com.fhs.filter;

import com.fhs.common.spring.SpringContextUtil;
import com.fhs.core.config.EConfig;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 初始化模板引擎中的参数
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = {"/*"}, filterName = "templateConfigSettFilter", asyncSupported = true)
public class TemplateConfigSettFilter implements Filter {

    /**
     * 是否初始化过
     */
    private boolean isInit = false;

    /**
     * Default constructor.
     */
    public TemplateConfigSettFilter() {
    }


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain filterChain)
            throws IOException, ServletException {
        if (!isInit) {
            BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = SpringContextUtil.getBeanByName(BeetlGroupUtilConfiguration.class);
            Set<String> keys = EConfig.PATH.stringPropertyNames();
            Map<String, Object> shared = new HashMap<String, Object>();
            for (String key : keys) {
                shared.put(key, EConfig.PATH.get(key));
                req.getServletContext().setAttribute(key, EConfig.PATH.get(key));
            }
            keys = EConfig.OTHER_CONFIG.stringPropertyNames();
            for (String key : keys) {
                shared.put(key, EConfig.OTHER_CONFIG.get(key));
                req.getServletContext().setAttribute(key, EConfig.OTHER_CONFIG.get(key));
            }
            // beetl共享变量，所有的模板都能访问到
            beetlGroupUtilConfiguration.getGroupTemplate().setSharedVars(shared);
            isInit = true;
        }
        filterChain.doFilter(req, rep);
    }


    public void init(FilterConfig fConfig)
            throws ServletException {
    }
}
