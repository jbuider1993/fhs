package com.fhs.common.utils;

import java.util.ArrayList;
import java.util.List;


/**
 * <类型转换工具类>
 *
 * @author zhaozhilong
 * @version [版本号, 2016年12月8日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ListUtils
{
    /**
     * 将字符串集合转换成整数集合
     * @param list
     * @return
     */
    public static List<Integer> listStringToInteger(List<String> list)
    {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < list.size(); i++)
        {
            result.add(ConverterUtils.toInteger(list.get(i)));
        }
        return result;
    }

    /**
     * 将list bean 中的一个字段 拼接成一个list
     * @param list 需要转换的list
     * @param field 字段名字
     * @return list的每一个bean get field 的结果add到一个list中
     */
    public static List<String> appendField(List<? extends Object> list,String field)
    {
        List<String> result = new ArrayList<>();
        for(Object object : list)
        {
            result.add(ConverterUtils.toString(ReflectUtils.getValue(object, field)));
        }
        return result;
    }

    /**
     * 数组转list
     * @param array  需要转list的数组
     * @param <T>
     * @return list
     */
    public static <T> List<T> array2List(T[] array)
    {
        List<T> result = new ArrayList<>();
        for(T t:array)
        {
            result.add(t);
        }
        return result;
    }
}