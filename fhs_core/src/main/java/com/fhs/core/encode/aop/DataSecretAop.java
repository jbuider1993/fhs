package com.fhs.core.encode.aop;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fhs.core.base.bean.BaseObject;
import com.fhs.core.encode.annotation.AesEncode;
import com.fhs.core.encode.annotation.AesEncodeClass;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import com.fhs.common.utils.Logger;

/**
 * 关键数据加密解密
 *
 * @Filename: DataSecretAop.java
 * @Description:
 * @Version: 1.0
 * @Author: yaoyang
 * @Email: 15947277970@163.com
 * @History:<br>
 * 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
public class DataSecretAop
{
    private static final Logger LOG = Logger.getLogger(DataSecretAop.class);

    /** 需要加密的方法 */
    private static Set<Method>  needEncodeMethod = new HashSet<>();

    /** 不需要加密的方法 */
    private static Set<Method> notEncodeMethod = new HashSet<>();

    /** 方法参数是否是List*/
    private static Map<Method, Boolean> paramIsCollection = new HashMap<>();

    /** 需要加密的类 对应的字段 */
    private static Map<Class<?>,List<Field>>  ojectNeedEncodeFieldMap = new HashMap<>();

    /** 不需要加密的类 */
    private static Set<Class<?>> notNeedEncodeClassSet = new HashSet<>();

    /** AES加密密码 */
    //private static String password = EConfig.getOtherConfigPropertiesValue("aes_password");

    public Object around(ProceedingJoinPoint joinPoint)
        throws Throwable
    {

        // 获取当前执行方法
        String methodName = joinPoint.getSignature().getName();
        Class<?> classTarget = joinPoint.getTarget().getClass();
        Class<?>[] par = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();
        Method method = classTarget.getMethod(methodName, par);
        // 获取方法所有参数
        Object[] args = joinPoint.getArgs();
        boolean isNeedEncode = false;
        if(args.length!=1 || notEncodeMethod.contains(method))
        {
            return returnDecodeResult(joinPoint.proceed(),method);
        }
        // 如果方法的参数只有一个，或者不需要加密的set中不包含该方法
        else if(needEncodeMethod.contains(method)) {
            isNeedEncode = true;
            if(paramIsCollection.containsKey(method))
            {
                // 获取泛型
                Collection<?> collectionParam =(Collection<?>)args[0];
                if(collectionParam!=null)
                {
                    for(Object object : collectionParam)
                    {
                       encodeBean(object);
                    }
                }
                 return returnDecodeResult(joinPoint.proceed(),method);
           }
            encodeBean(args[0]);
            return returnDecodeResult(joinPoint.proceed(),method);
        }
        else  if (args.length==1)
        {
            // 判断参数是否是Collection类型
            if (args[0] instanceof Collection)
            {
                // 映射方法与参数是list的关系
                paramIsCollection.put(method, true);

                // 获取泛型
                Collection<?> collectionParam =(Collection<?>)args[0];
                if(collectionParam!=null && collectionParam.size() !=0)
                {
                    for(Object object : collectionParam)
                    {
                        if (isNeedEncode || object instanceof BaseObject<?>)
                        {
                            isNeedEncode = encodeBean(object);
                        }
                    }
                }

            }else {
                isNeedEncode = encodeBean(args[0]);
            }
            if (isNeedEncode)
            {
                needEncodeMethod.add(method);
            }
            else {
                notEncodeMethod.add(method);
            }
            return returnDecodeResult(joinPoint.proceed(),method);
        }
        notEncodeMethod.add(method);
        return returnDecodeResult(joinPoint.proceed(),method);

    }

    /**
     * 对类的一些字段进行加密
     * @param element
     */
    private Boolean encodeBean(Object element)
    {
        List<Field> fieldList =null;
        if (ojectNeedEncodeFieldMap.containsKey(element.getClass()))
        {
            fieldList = ojectNeedEncodeFieldMap.get(element.getClass());
        }else if (notNeedEncodeClassSet.contains(element.getClass()))
        {
            return false;
        }else {

            fieldList = new ArrayList<Field>();
            // 判断是否有AesEncodeClass注解

            // 没有需要加密的注解
            if (null == element.getClass().getAnnotation(AesEncodeClass.class))
            {
                notNeedEncodeClassSet.add(element.getClass());
                return false;
            }else {
                // 获取所有字段
                Field[] fields = element.getClass().getDeclaredFields();
                for (Field field : fields)
                {
                    // 如果字段上有需要加密的注解
                    if (null != field.getAnnotation(AesEncode.class))
                    {
                        // 将字段添加进去
                        fieldList.add(field);
                    }
                }
            }
        }
        // 将class 和list put到中
        ojectNeedEncodeFieldMap.put(element.getClass(), fieldList);

        // 遍历所有字段，获取值，加密
        for (Field field : fieldList)
        {
            field.setAccessible(true);

            try
            {
                if(null != field.get(element)) {
                    //field.set(element, AESUtil.encrypt(field.get(element).toString(),password));
                }
            }
            catch (IllegalArgumentException | IllegalAccessException e)
            {
                LOG.error("字段加密出现异常", e);
            }
        }

        return true;
    }

    /** 需要解码的方法 */
    private static Set<Method>  needDecodeMethod = new HashSet<>();

    /** 不需要解码的方法 */
    private static Set<Method> notDecodeMethod = new HashSet<>();

    /** 返回参数是否是List*/
    private static Map<Method, Boolean> resultIsCollection = new HashMap<>();

    /** 需要解码的类 对应的字段 */
    private static Map<Class<?>,List<Field>>  ojectNeedDecodeFieldMap = new HashMap<>();

    /** 不需要解码的类 */
    private static Set<Class<?>> notNeedDecodeClassSet = new HashSet<>();

    /**
     * 返回值解码
     * @param result
     * @return
     */
    public Object returnDecodeResult(Object result,Method method) {

        boolean isNeedDecode = false;
        //走缓存 判断这个方法是否需要解码
        if (notDecodeMethod.contains(method))
        {
            return result;
        }else if (needDecodeMethod.contains(method))
        {
            isNeedDecode = true;
            // 走缓存 判断参数是否是集合
            if (resultIsCollection.get(method)==null?false:resultIsCollection.get(method))
            {
                // 获取泛型
                Collection<?> collectionParam =(Collection<?>)result;
                if(collectionParam!=null)
                {
                    for(Object object : collectionParam)
                    {
                        decodeBean(object);
                    }
                }
                return collectionParam;
            }
            decodeBean(result);
            return result;
        }else
        {
            // 判断参数是否是Collection类型
            if (result instanceof Collection)
            {
                // 获取泛型
                Collection<?> collectionParam =(Collection<?>)result;
                if(collectionParam!=null && collectionParam.size() !=0)
                {
                    for(Object object : collectionParam)
                    {
                        if (isNeedDecode || object instanceof BaseObject<?>)
                        {
                            isNeedDecode = decodeBean(object);
                        }
                    }
                }

                if (isNeedDecode)
                {
                    needDecodeMethod.add(method);
                    // 映射方法与参数是list的关系
                    resultIsCollection.put(method, true);
                }
                else {
                    notDecodeMethod.add(method);
                }
                return collectionParam;

            }else if (result instanceof BaseObject<?>){
                isNeedDecode = decodeBean(result);
            }

            if (isNeedDecode)
            {
                needDecodeMethod.add(method);
            }
            else {
                notDecodeMethod.add(method);
            }
            return result;
        }
    }

    /**
     * 解码返回值
     * @return
     */
    public Boolean decodeBean(Object element) {

        List<Field> fieldList =null;
        if (ojectNeedDecodeFieldMap.containsKey(element.getClass()))
        {
            fieldList = ojectNeedDecodeFieldMap.get(element.getClass());
        }else if (notNeedDecodeClassSet.contains(element.getClass()))
        {
            return false;
        }else {

            fieldList = new ArrayList<Field>();
            // 判断是否有AesDecodeClass注解

            // 没有需要解码的注解
            if (null == element.getClass().getAnnotation(AesEncodeClass.class))
            {
                notNeedDecodeClassSet.add(element.getClass());
                return false;
            }else {
                // 获取所有字段
                Field[] fields = element.getClass().getDeclaredFields();
                for (Field field : fields)
                {
                    // 如果字段上有需要解码的注解
                    if (null != field.getAnnotation(AesEncode.class))
                    {
                        // 将字段添加进去
                        fieldList.add(field);
                    }
                }
            }
        }
        // 将class 和list put到中
        ojectNeedDecodeFieldMap.put(element.getClass(), fieldList);

        // 遍历所有字段，获取值，解密
        for (Field field : fieldList)
        {
            field.setAccessible(true);

            try
            {
                if(null != field.get(element)) {
                    //field.set(element, AESUtil.decrypt(field.get(element).toString(),password));
                }
            }
            catch (IllegalArgumentException | IllegalAccessException e)
            {
                LOG.error("字段解码出现异常", e);
            }
        }

        return true;
    }
}
