package com.fhs.core.api.event;

import com.mybatis.jpa.common.scanner.AnnotationTypeFilterBuilder;
import com.mybatis.jpa.common.scanner.SpringClassScanner;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.api.annotation.AutowareYLM;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

/**
 *  对添加@AutowareYLM注解的field赋值
 *  注意：如果一个项目是提供者又是消费者，优先注入原始对象，而不是fegin代理对象
 * @Filename: AutowareYLMSetBeanEvent.java
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class AutowareYLMSetBeanEvent implements ApplicationListener<ContextRefreshedEvent> {

    private String[] packageNames;

    public String[] getPackageNames() {
        return packageNames;
    }

    public void setPackageNames(String[] packageNames) {
        this.packageNames = packageNames;
    }

    private static boolean isInited = false;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(isInited)
        {
            return;
        }
        isInited = true;
        //spring容器初始化完成之后，就会自行此方法。
        // 获取到所有用@autowareYLM注释的class
        TypeFilter entityFilter = AnnotationTypeFilterBuilder.build(AutowareYLM.class);
        SpringClassScanner entityScanner = new SpringClassScanner.Builder().typeFilter(entityFilter).build();

        for(String packageName : packageNames)
        {
            entityScanner.getScanPackages().add(packageName);
        }
        Set<Class<?>> entitySet = null;
        try {
            entitySet = entityScanner.scan();
        } catch (ClassNotFoundException | IOException e) {
            // log or throw runTimeExp
            throw new RuntimeException(e);
        }
        // 如果还没初始化，手动给一个
        if(SpringContextUtil.getApplicationContext () == null)
        {
            SpringContextUtil.setStaticApplicationContext (contextRefreshedEvent.getApplicationContext ());
        }
        // 遍历所有class，获取所有用@autowareYLM注释的字段
        if (entitySet != null && !entitySet.isEmpty()) {
            for (Class<?> entity : entitySet) {
                // 获取该类
                Object object = SpringContextUtil.getBeanByClass(entity);
                Field[] fields = entity.getDeclaredFields();
                if(CheckUtils.isNotEmpty(fields)){
                    for (Field field : fields)
                    {
                        if(field.isAnnotationPresent(AutowareYLM.class))
                        {
                            // 获取到该字段的对象
                            Object serviceObject = SpringContextUtil.getBeanByClassForApi(field.getType());
                            // 为该类下，该字段名赋值
                            ReflectUtils.setValue(object, field.getName(), serviceObject);
                        }
                    }
                }
            }
        }


    }

}