package com.fhs.config;

import com.fhs.interceptor.IndexInterceptor;
import com.fhs.interceptor.IpWhiteInterceptor;
import com.fhs.interceptor.LoginInterceptor;
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
        InterceptorRegistration irLogin = registry.addInterceptor (new LoginInterceptor ());
        InterceptorRegistration irIndex = registry.addInterceptor (new IndexInterceptor ());
        InterceptorRegistration irIpWhite = registry.addInterceptor (new IpWhiteInterceptor ());

        irLogin.addPathPatterns("/**");
        irIndex.addPathPatterns ("/ms/**");
        irIpWhite.addPathPatterns ( "/**" );
        irIpWhite.excludePathPatterns("/api/*");
        super.addInterceptors(registry);
//        irLogin.excludePathPatterns("/**.html");  // 配置不拦截的路径
    }
}
