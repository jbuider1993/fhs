package com.fhs.module.base.config;

import com.fhs.core.valid.aop.FeignParamCheckAop;
import com.fhs.core.valid.aop.OrdinaryActionParamCheckAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FHSAopConfig {

    /**
     * 参数校验aop
     * @return
     */
    @Bean
    public OrdinaryActionParamCheckAop getParamCheckAop(){
        return  new OrdinaryActionParamCheckAop();
    }

    /**
     * fegin接口校验
     * @return
     */
    @Bean
    public FeignParamCheckAop getFeignParamCheckAop(){
        return  new FeignParamCheckAop();
    }

}
