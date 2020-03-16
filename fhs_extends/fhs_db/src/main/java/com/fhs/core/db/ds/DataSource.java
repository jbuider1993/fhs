package com.fhs.core.db.ds;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于显示的指定哪个数据源
 * @Filename: DataSource.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@Inherited
@Target(value={ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public  @interface DataSource
{
    /**
     * 获取值
     * @return
     */
    public String value() default "";
}

