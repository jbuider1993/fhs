package com.fhs.core.responsebody.annotation;


import java.lang.annotation.*;

/**
 * json
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonFilter {
    ObjFilter[] value();
}