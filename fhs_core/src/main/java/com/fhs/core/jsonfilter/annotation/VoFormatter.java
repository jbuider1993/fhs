package com.fhs.core.jsonfilter.annotation;

import java.lang.annotation.*;

/**
 * VO转换器
 * by wanglei
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface VoFormatter {
    String[] value();

    /**
     * 包含的字段
     * @return
     */
    String[] include() ;
}
