package com.fhs.config;

import com.fhs.core.exception.ExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExceptionHandlerConfig {

    /**
     * 自定义全局错误
     * @return
     */
    @Bean
    public ExceptionHandler getExceptionHandler(){
        return new ExceptionHandler();
    }
}
