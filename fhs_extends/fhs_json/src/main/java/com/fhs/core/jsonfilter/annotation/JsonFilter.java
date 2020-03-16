package com.fhs.core.jsonfilter.annotation;


import java.lang.annotation.*;

/**
 * json过滤器
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonFilter {
    ObjFilter[] value();
}