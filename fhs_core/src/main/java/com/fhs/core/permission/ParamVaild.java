package com.fhs.core.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数校验
 * @Filename: ParamVaild.java
 * @Description:
 * @Version: 1.0
 * @Author: qh
 * @History:<br>
 * Copyright (c) 2018 All Rights Reserved.
 *
 */
@Target(value={ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ParamVaild {
}
