package com.fhs.core.dodefault.sysdefault;

import java.lang.reflect.Field;

public interface ISysDefaultHandel
{
    /**
     * 设置默认值
     * 
     * @param obj obj
     * @param field 字段
     * @param pattern 表达式
     */
    void setDefaultVal(Object obj, Field field, String... pattern);
}
