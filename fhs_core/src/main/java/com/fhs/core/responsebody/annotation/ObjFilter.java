package com.fhs.core.responsebody.annotation;

import java.lang.annotation.*;

/**
 * json过滤器
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ObjFilter {

    /**
     * 比如返回ClassRoom对象，里面有个属性是Student 可以写stus
     * @return
     */
    String expression() default  "";

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