package com.fhs.core.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限认证
 * @Filename: PermissionCode.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
@Target(value={ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionCode
{
    /**
     * 获取值
     * @return
     */
    public String value() default "";
}
