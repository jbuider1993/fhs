package com.fhs.core.jsonfilter.anno;


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