package com.fhs.core.jsonfilter.anno;

import java.lang.annotation.*;

/**
 * json过滤器
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ObjFilter {

    /**
     * 类
     */
    Class clazz();

    /**
     * 哪些字段可以返回
     * @return 哪些字段可以返回
     */
    String[] includeField() default {};

    /**
     * 哪些字段不能返回
     * @return 哪些字段不能返回
     */
    String[] excludeField() default {};
}