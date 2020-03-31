package com.fhs.core.safe.repeat.anno;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记方法不可重入
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface NotRepeat {
    /**
     * 自动过期时间
     * @return
     */
    int outTime() default 10;
}
