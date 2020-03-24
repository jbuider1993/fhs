package com.fhs.module.base.config;

import com.fhs.core.feign.autowired.handle.AutowiredFhsSetBeanHandle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动注入配置
 */
@Configuration
public class AutowiredFhsConfig {

    @Value("${fhs.autoware.package}")
    private String basePackge;

    @Bean
    public AutowiredFhsSetBeanHandle getAutowareYLMSetBeanEvent(){
        AutowiredFhsSetBeanHandle autowareYLMSetBeanEvent = new AutowiredFhsSetBeanHandle();
        autowareYLMSetBeanEvent.setPackageNames (basePackge.split(";"));
        return  autowareYLMSetBeanEvent;
    }
}
