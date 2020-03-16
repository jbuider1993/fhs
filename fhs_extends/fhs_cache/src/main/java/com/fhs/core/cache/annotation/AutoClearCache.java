package com.fhs.core.cache.annotation;

import java.lang.annotation.*;

/**
 * fhs framework 对于 jetcahce的扩展注解
 * 用于namespaces  的数据改变之后 自动清除缓存
 * by jackwong
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.TYPE,})
public @interface AutoClearCache {
    /**
     * 需要自动清除缓存的命名空间
     * @return
     */
    String[] namespaces() default {};
}
