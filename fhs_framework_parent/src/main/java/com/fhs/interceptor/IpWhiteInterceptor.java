package com.fhs.interceptor;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.Logger;
import com.fhs.common.utils.NetworkUtil;
import com.fhs.core.config.EConfig;
import com.fhs.core.result.HttpResult;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * ip 白名单拦截器
 * @Filename: IpWhiteInterceptor.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@Component
public class IpWhiteInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

    /**
     * @desc 本地日志
     */
    private final static Logger LOGGER = Logger.getLogger(IpWhiteInterceptor.class);

    /**
     *ip白名单集合
     */
    private static Set<String> whiteIpSet = new HashSet<>();

    @Autowired
    EConfig config;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        boolean isCheckWhiteIp = Constant.STR_TRUE.equals(EConfig.getOtherConfigPropertiesValue("isCheckIp"));
        //有关fegin接口的ip白名单过滤 end

        // 如果不check白名单直接通过
        if(!isCheckWhiteIp)
        {
            return true;
        }
        //有关feign接口的ip白名单过滤 start
        Class<?> returnType = null;
        if(handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod)handler;
            returnType = handlerMethod.getMethod ( ).getReturnType ( );//问题：无法获取返回httpresult的泛型,暂时此法不通
        }
        //如果check白名单，并且当前ip符合规则配置
        if (whiteIpSet.contains( NetworkUtil.getIpAddress(request))){
           return true;
        }
        String errorJson = HttpResult.error (new Object(), "ip无权调用接口，请联系接口人配置ip白名单" ).asJson ();
        JsonUtils.outJson (response, errorJson);
        return false;
    }



    /**
     * @desc 获取当前IP白名单的set集合
     * @return
     */
    private void refreshWhiteIpSet() {
        Set<String> whiteIpSets = new HashSet<>();
        String ipWhiteRuleStr = ConverterUtils.toString ( EConfig.getOtherConfigPropertiesValue ( "ip_white_list_rule" ) );
        String[] ipWhiteRules = ipWhiteRuleStr.split ( ";" );
        whiteIpSets.addAll ( Arrays.asList (ipWhiteRules) );
        whiteIpSets.add("0:0:0:0:0:0:0:1");
        whiteIpSets.add("127.0.0.1");
        whiteIpSet = whiteIpSets;
    }


    public void refresh(Properties pathConfig, Properties otherConfig) {
        refreshWhiteIpSet();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        config.registerConfigChangeListener(this::refresh);
        refreshWhiteIpSet();
    }

    public void setWhiteIpSet(Set<String> whiteIpSet)
    {
        IpWhiteInterceptor.whiteIpSet = whiteIpSet;
    }

    public static Set<String> getWhiteIpSet()
    {
        return whiteIpSet;
    }


}
