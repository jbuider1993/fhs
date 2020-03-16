package com.fhs.core.trans.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE})
public @interface TransTypes 
{
    /**
     * 获取需要翻译的类型
     * @return
     */
    String[] types();
}
