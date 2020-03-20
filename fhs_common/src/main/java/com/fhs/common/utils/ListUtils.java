package com.fhs.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

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
@Slf4j
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


    /**
     * 将entityList转换成modelList
     * @param fromList
     * @param tClass
     * @param <F>
     * @param <T>
     * @return
     */
    public static<F,T> List<T> copyListToList(List<F> fromList, Class<T> tClass) {
        if (fromList.isEmpty()) {
            return null;
        }
        List<T> tList = new ArrayList<>();
        for (F f : fromList) {
            T t = entityToModel(f, tClass);
            if (t != null) {
                tList.add(t);
            }
        }
        return tList;
    }

    /**
     * 把子类copy为父类  语法糖
     * @param fromList  子类list
     * @param tClass 父类class
     * @param <F>
     * @param <T>
     * @return
     */
    public static<T> List<T> copyListToPararentList(List<?> fromList, Class<T> tClass){
        List tempList = fromList;
        return tempList;
    }

    private static<F,T> T entityToModel(F entity, Class<T> modelClass) {
        T model = null;
        if (entity == null || modelClass ==null) {
            return null;
        }
        try {
            model = modelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("实体类实例化异常");
        }
        BeanUtils.copyProperties(entity, model);
        return model;
    }
}