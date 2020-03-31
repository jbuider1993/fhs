package com.fhs.core.base.autodel.anno;


import com.fhs.core.base.service.impl.BaseServiceImpl;

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
public @interface AutoDelSett {

    /**
     * 主表的service
     * @return
     */
   Class<? extends BaseServiceImpl> mainServiceClazz();

    /**
     * 主表主键对应我表的字段名 注意 index 需要和mainServiceClazz 对应上
     * @return
     */
   String field();

    /**
     * 是否校验子类被删除
     * 如果给true的话,子类没有删除完之前父类不可删除
     * @return
     */
   boolean isChecker() default false;

    /**
     * 本类描述
     * @return
     */
    String desc() default "";
}
