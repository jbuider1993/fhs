package com.fhs.module.base.config;

import com.fhs.logger.Logger;
import org.springframework.aop.TargetSource;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:根据条件创建代理对象，用于声明式事物
 * @author  jackwong
 * @version  [版本号, 2018/03/29 14:04:42]
 * @versio  1.0
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 * */
public class MyBeanNameAutoProxyCreator extends AbstractAutoProxyCreator {

    private static Logger LOG = Logger.getLogger(MyBeanNameAutoProxyCreator.class);

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

    Map<String,Boolean> packageMap = new HashMap<>();

    @Override
    protected Object[] getAdvicesAndAdvisorsForBean(Class<?> beanClass, String beanName, TargetSource customTargetSource) throws BeansException {

        String beanClassName = beanClass.getName();
        LOG.debug("check bean " + beanClass);
        if(beanClass.getPackage()==null)
        {
            return DO_NOT_PROXY;
        }
        String packgerName = beanClass.getPackage().getName();
        if(packageMap.containsKey(packgerName))
        {
            if(packageMap.get(packgerName))
            {
                return PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS;
            }
            return DO_NOT_PROXY;
        }
        if(packgesArray==null)
        {
            packgesArray = packge.split(";");
            containsArray = contains.split(";");
            notContainsArray = notContains.split(";");
        }
        packageMap.put(packgerName,false);

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
        packageMap.put(packgerName,true);
        return PROXY_WITHOUT_ADDITIONAL_INTERCEPTORS;
    }

}
