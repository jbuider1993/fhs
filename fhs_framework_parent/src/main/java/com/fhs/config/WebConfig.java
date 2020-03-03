package com.fhs.config;

import com.fhs.common.utils.Logger;
import com.fhs.core.safe.csrf.CSRFFileter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * tomcat配置
 */
@Configuration
public class WebConfig   implements EnvironmentAware, WebMvcConfigurer {

    /*
     *日志
     */
    private static final Logger LOGGER = Logger.getLogger(WebConfig.class);


    private Environment environment;





    /**
     * csrf漏洞修复过滤器
     */
    @Bean
    public FilterRegistrationBean csrfFileter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setName("csrfFileter");
        bean.setFilter(new CSRFFileter());
        bean.addUrlPatterns("/ms/*", "/page/ms/*");
        bean.setEnabled(true);
        return bean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
