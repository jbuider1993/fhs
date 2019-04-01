package com.fhs.config;

import com.fhs.core.api.event.AutowareYLMSetBeanEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AutowareYLMConfig {

    @Value("${fhs.autoware.package}")
    private String basePackge;

    @Bean
    public AutowareYLMSetBeanEvent getAutowareYLMSetBeanEvent(){
        AutowareYLMSetBeanEvent autowareYLMSetBeanEvent = new AutowareYLMSetBeanEvent();
        autowareYLMSetBeanEvent.setPackageNames (basePackge.split(";"));
        return  autowareYLMSetBeanEvent;
    }
}
