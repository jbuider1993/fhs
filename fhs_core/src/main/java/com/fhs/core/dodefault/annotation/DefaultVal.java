package com.fhs.core.dodefault.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fhs.core.dodefault.sysdefault.DateDefault;

/**
 * 参数默认值注解
 * 
 * @author jianbo.qin
 *
 */
@Inherited
@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DefaultVal
{
    /*
     * 来源 sys-系统默认 input-用户输入
     */
    SourceEnum source();
    
    /*
     * 数据类型
     */
    TypeEnum type() default TypeEnum.TEXT;
    
    /*
     * 格式化参数
     */
    DateDefault.Date pattern() default DateDefault.Date.DATETIME_PATTERN;
    
    /*
     * 用户输入值
     */
    String val() default "";
    
    public enum TypeEnum
    {
        DATE, // 日期类型
        
        TEXT; // 文本类型
    }
    
    /*
     * 定义参数枚举
     */
    public enum SourceEnum
    {
        SYS, // 系统默认
        
        INPUT; // 输入
    }
    
}
