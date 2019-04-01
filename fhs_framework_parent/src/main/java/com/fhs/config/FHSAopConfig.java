package com.fhs.config;

import com.fhs.aop.ActionInterceptAndCreateLogAop;
import com.fhs.core.validation.FeignParamCheckAop;
import com.fhs.core.validation.ParamCheckAop;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FHSAopConfig {

    /**
     * 参数校验aop
     * @return
     */
    @Bean
    public ParamCheckAop getParamCheckAop(){
        return  new ParamCheckAop();
    }

    /**
     * fegin接口校验
     * @return
     */
    @Bean
    public FeignParamCheckAop getFeignParamCheckAop(){
        return  new FeignParamCheckAop();
    }

    /**
     * 日志记录aop
     * @return
     */
    @Bean
    public ActionInterceptAndCreateLogAop getActionInterceptAndCreateLogAop(){
        return  new ActionInterceptAndCreateLogAop ();
    }

    /**
     * ucenter前端用户权限校验aop
     * @return
     *//*
    @Bean
    public PermissionCheckAop getPermissionCheckAop(){
        return new PermissionCheckAop();
    }*/
}
