package com.fhs.core.base.autodel.anno;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 此注解只可标记到service上，标记之后如果主表被软删除，那么此表相关数据也自动被软删除
 *
 * @Author: Wanglei
 * @Date: Created in 10:14 2019/10/15
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface AutoDel {

    /**
     * 主表的
     * @return
     */
    AutoDelSett[] mainServiceSetts() default {};



}
