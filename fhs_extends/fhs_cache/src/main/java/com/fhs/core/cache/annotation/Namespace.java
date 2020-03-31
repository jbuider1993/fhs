package com.fhs.core.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 此注解只可标记到service上，为业务别名
 * @Author: Wanglei
 * @Date: Created in 10:14 2019/10/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface Namespace
{
    /**
     *  命名空间
     * @return
     */
    String value();

}
