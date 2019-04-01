package com.fhs.config;

import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Description:根据条件创建代理对象，用于声明式事物
 * @author  jackwong
 * @version  [版本号, 2018/03/29 14:04:42]
 * @versio  1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 * */
@Component("myBeanNameAutoProxyCreator")
public class MyBeanNameAutoProxyCreator extends AbstractAutoProxyCreator {

    /**
     * 哪些包需要代理
     */
    @Value("${fhs.transcatoin.package}")
    private String packge;

    private String[] packgesArray;

    @Value("${fhs.transcatoin.contains}")
    private String contains;

    private String[] containsArray;

    @Value("${fhs.transcatoin.not-contains}")
    private String notContains;

    private String[] notContainsArray;

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {
        String beanClassName = beanClass.getName();
        if(packgesArray==null)
        {
            packgesArray = packge.split(";");
            containsArray = contains.split(";");
            notContainsArray = notContains.split(";");
        }
        boolean isStartWith = false;
        for(String packge:packgesArray)
        {
            if(beanClassName.startsWith(packge))
            {
                isStartWith =true;
                break;
            }
        }
        //如果不startwith 返回不需要代理
        if(!isStartWith)
        {
            return DO_NOT_PROXY;
        }
        boolean isContains = false;
        for(String contains:containsArray)
        {
            if(beanClassName.contains(contains))
            {
                isContains =true;
                break;
            }
        }
        //如果不isContains 返回不需要代理
        if(!isContains)
        {
            return DO_NOT_PROXY;
        }
        //遍历必须不包含的，如果包含了，返回不需要代理
        for(String notContains :notContainsArray)
        {
            if(beanClassName.contains(notContains))
            {
                return DO_NOT_PROXY;
            }
        }
        return PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS;
    }

}
