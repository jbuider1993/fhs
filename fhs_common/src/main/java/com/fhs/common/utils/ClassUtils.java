package com.fhs.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtils
{
    public static void fatherToChild(Object father, Object child)
        throws Exception
    {
        if (!(child.getClass().getSuperclass() == father.getClass()))
        {
            throw new Exception("child不是father的子类");
        }
        Class<?> fatherClass = father.getClass();
        Field ff[] = fatherClass.getDeclaredFields();
        for (int i = 0; i < ff.length; i++)
        {
            Field f = ff[i];// 取出每一个属性，如deleteDate
            f.setAccessible(true); // 该方法表示取消java语言访问检查

            if (ConverterUtils.toString(f).contains("static"))
            {
                continue;
            }

            Method m = fatherClass.getMethod("get" + upperHeadChar(f.getName()));// 方法getDeleteDate
            Object obj = m.invoke(father);// 取出属性值
            f.set(child, obj);
        }
    }

    /**
     * 首字母大写，in:deleteDate，out:DeleteDate
     */
    public static String upperHeadChar(String in)
    {
        String head = in.substring(0, 1);
        String out = head.toUpperCase() + in.substring(1, in.length());
        return out;
    }
}
