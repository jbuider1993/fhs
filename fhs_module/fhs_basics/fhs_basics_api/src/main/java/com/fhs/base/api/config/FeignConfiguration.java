package com.fhs.base.api.config;

import feign.Contract;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Filename: FeignConfiguration.java
 * @Description: feign配置类
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@Configuration
public class FeignConfiguration implements RequestInterceptor {

    @Value("${fhs.api.password:fhs-framework}")
    private String apiToken;

    public String getApiToken(){
        return apiToken;
    }

    @Bean
    public Contract myFeignConfiguration()
    {
        return new Contract.Default();
    }
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Override
    public void apply(RequestTemplate template) {
        template.header("apiToken",apiToken);
    }
}
