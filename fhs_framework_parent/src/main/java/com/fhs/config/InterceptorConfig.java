package com.fhs.config;

import com.fhs.interceptor.IpWhiteInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 拦截器配置类
 * @author qh
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration irIpWhite = registry.addInterceptor (new IpWhiteInterceptor ());
        irIpWhite.addPathPatterns ( "/**" );
        irIpWhite.excludePathPatterns("/api/*");
        super.addInterceptors(registry);
//        irLogin.excludePathPatterns("/**.html");  // 配置不拦截的路径
    }
}
