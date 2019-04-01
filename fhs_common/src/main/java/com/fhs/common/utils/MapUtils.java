package com.fhs.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * map工具类
 *
 * @author wanglei
 * @version [版本号, 2015年7月2日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class MapUtils
{
    /**
     * 根据条件将符合条件的key 匹配出来并返回
     *
     * @param pattern 匹配条件
     * @param map 需要查找key的map
     * @return 符合条件key的集合
     */
    public static List<String> getKeys(String pattern, Map<String, ? extends Object> map)
    {
        Set<String> keys = map.keySet();
        boolean isStartWtidCheck = false;
        boolean isEndWtidCheck = false;
        String startWidthStr = null;
        String endWithStr = null;
        List<String> resultKeys = new ArrayList<String>();
        if (pattern.startsWith("*"))
        {
            isEndWtidCheck = true;
            endWithStr = pattern.replace("*", "");
        }
        else if (pattern.endsWith("*"))
        {
            isStartWtidCheck = true;
            startWidthStr = pattern.replace("*", "");
        }
        else
        {
            isStartWtidCheck = true;
            isEndWtidCheck = true;
            String[] tempArray = pattern.split("*");
            startWidthStr = tempArray[0];
            endWithStr = tempArray[1];
        }
        for (String key : keys)
        {
            // 如果两个条件都具备才可以添加
            if (isStartWtidCheck && isEndWtidCheck && key.startsWith(startWidthStr) && key.endsWith(endWithStr))
            {
                resultKeys.add(key);
            }
            // 如果只匹配结尾
            else if (isEndWtidCheck && key.endsWith(endWithStr))
            {
                resultKeys.add(key);
            }
            // 如果只匹配开始
            else if (isStartWtidCheck && key.startsWith(startWidthStr))
            {
                resultKeys.add(key);
            }
        }
        return resultKeys;
    }

    /**
     * 清除空的key 设置为null
     *
     * @param map 需要清除的map
     * @return 清除后的map
     */
    public static Map<String, Object> clearEmptyKey(Map<String, Object> map)
    {
        Set<String> set = map.keySet();
        for (String key : set)
        {
            if (CheckUtils.isNullOrEmpty(map.get(key)))
            {
                map.put(key, null);
            }
        }
        return map;
    }

    /**
     * 将bean转换为map
     *
     * @param obj 需要转换的bean
     * @return map
     */
    public static Map<String, Object> bean2Map(Object obj)
    {

        if (obj == null)
        {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try
        {
            List<Field> fieldList = ReflectUtils.getAllField(obj);
            Field field = null;
            // 循环时倒序，先给父类属性赋值，后给子类属性赋值
            for (int i = fieldList.size() - 1; i >= 0; i--)
            {
                field = fieldList.get(i);
                // 如果属性修饰符是private static final,跳过
                if (field.getModifiers() == 26)
                {
                    continue;
                }
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        }
        catch (Exception e)
        {
            System.out.println("transBean2Map Error " + e);
        }

        return map;

    }

    /**
     * map 转 Bean
     *
     * @param <T>
     *
     * @param map
     * @param cls
     * @return
     */
    public static <T> T map2Bean(@SuppressWarnings("rawtypes") Map map, Class<T> cls)
    {
        T obj = null;
        try
        {
            obj = cls.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        // 取出bean里的所有方法
        Method[] methods = cls.getMethods();
        for (int i = 0; i < methods.length; i++)
        {
            // 取方法名
            String method = methods[i].getName();
            // 取出方法的类型
            Class<?>[] cc = methods[i].getParameterTypes();
            if (cc.length != 1)
                continue;

            // 如果方法名没有以set开头的则退出本次for
            if (method.indexOf("set") < 0)
                continue;
            // 类型
            String type = cc[0].getSimpleName();

            try
            {
                // 转成小写
                // Object value = method.substring(3).toLowerCase();
                Object value = method.substring(3, 4).toLowerCase() + method.substring(4);
                // 如果map里有该key
                if (map.containsKey(value) && map.get(value) != null)
                {
                    // 调用其底层方法
                    setValue(type, map.get(value), i, methods, obj);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static void setValue(String type, Object value, int i, Method[] method, Object bean)
    {
        if (value != null && !value.equals(""))
        {
            try
            {
                if (type.equals("String"))
                {
                    // 第一个参数:从中调用基础方法的对象 第二个参数:用于方法调用的参数
                    method[i].invoke(bean, new Object[] {value});
                }
                else if (type.equals("int") || type.equals("Integer"))
                {
                    method[i].invoke(bean, new Object[] {new Integer("" + value)});
                }
                else if (type.equals("double") || type.equals("Double"))
                {
                    method[i].invoke(bean, new Object[] {new Double("" + value)});
                }
                else if (type.equals("float") || type.equals("Float"))
                {
                    method[i].invoke(bean, new Object[] {new Float("" + value)});
                }
                else if (type.equals("long") || type.equals("Long"))
                {
                    method[i].invoke(bean, new Object[] {new Long("" + value)});
                }
                else if (type.equals("boolean") || type.equals("Boolean"))
                {
                    method[i].invoke(bean, new Object[] {Boolean.valueOf("" + value)});
                }
                else if (type.equals("BigDecimal"))
                {
                    method[i].invoke(bean, new Object[] {new BigDecimal("" + value)});
                }
                else if (type.equals("Date"))
                {
                    Date date = null;
                    if (value.getClass().getName().equals("java.util.Date"))
                    {
                        date = (Date)value;
                    }
                    else
                    {
                        String format = ((String)value).indexOf(":") > 0 ? "yyyy-MM-dd hh:mm:ss" : "yyyy-MM-dd";
                        SimpleDateFormat sf = new SimpleDateFormat();
                        sf.applyPattern(format);
                        date = sf.parse((String)(value));
                    }
                    if (date != null)
                    {
                        method[i].invoke(bean, new Object[] {date});
                    }
                }
                else if (type.equals("byte[]"))
                {
                    method[i].invoke(bean, new Object[] {new String(value + "").getBytes()});
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    /**
     * 非空验证
     *
     * @param paramMap
     * @param checkField 必填字段
     * @return 200 验证成功
     */
    public static String isNotService(Map<String, Object> paramMap, String... checkField)
    {
        try
        {
            // 字段
            String nullField = checkParam(paramMap, checkField);
            if (nullField.length() != 0)
            {
                return "field is null:" + nullField;
            }
        }
        catch (Exception e)
        {
            e.getStackTrace();
            return "serverError";
        }
        return "200";

    }

    /**
     * 判断map中的key是否为空或者为null
     *
     * @param paramMap
     * @param checkField 必填字段
     * @return 都不为空并且不为null 返回200 验证成功
     */
    public static String validateFields(Map<String, Object> paramMap, String... checkField)
    {
        try
        {
            // 字段
            String nullField = checkFields(paramMap, checkField);
            if (nullField.length() != 0)
            {
                return "field is null or empty:" + nullField;
            }
        }
        catch (Exception e)
        {
            e.getStackTrace();
            return "serverError";
        }
        return "200";

    }

    /**
     * 获取哪些参数为空
     *
     * @param paramMap 参数map
     * @param checkField 需要检查的字段集合
     * @return 如果没有错误返回"" 如果有错误返回 错误的字段用逗号分割
     */
    public static String checkParam(Map<String, Object> paramMap, String... checkField)
    {
        List<String> nullFieldList = new ArrayList<>();
        if (checkField == null)
        {
            return "";
        }
        for (String fieldName : checkField)
        {
            if (!paramMap.containsKey(fieldName))
            {
                nullFieldList.add(fieldName);
            }
        }
        if (nullFieldList.size() == 0)
        {
            return "";
        }
        return StringUtil.getStrForIntegerIn(nullFieldList);
    }

    /**
     * 获取哪些参数为空或者为null
     *
     * @param paramMap 参数map
     * @param checkField 需要检查的字段集合
     * @return 如果没有错误返回"" 如果有错误返回 错误的字段用逗号分割
     */
    public static String checkFields(Map<String, Object> paramMap, String... checkField)
    {
        List<String> nullFieldList = new ArrayList<>();
        if (checkField == null)
        {
            return "";
        }
        for (String fieldName : checkField)
        {
            if (!paramMap.containsKey(fieldName) || CheckUtils.isNullOrEmpty(paramMap.get(fieldName)))
            {
                nullFieldList.add(fieldName);
            }
        }
        if (nullFieldList.size() == 0)
        {
            return "";
        }
        return StringUtil.getStrForIntegerIn(nullFieldList);
    }

    /**
     * 根据参数组织一个map 单数 为key 双数为val
     *
     * @param argsArray 参数数组
     * @return map
     */
    public static Map<? extends Object, Object> getMapFromArgs(Object... argsArray)
    {
        Map<Object, Object> resultMap = new HashMap<>();
        for (int i = 0; (i + 1) < argsArray.length; i = (i + 2))
        {
            resultMap.put(argsArray[i], argsArray[i + 1]);
        }
        return resultMap;
    }

    /**
     * 将list中的某个参数当key,某个参数当value组建map
     *
     * @param list
     * @param key map的key对应的list中字段的key
     * @param value map的value对应的list中字段的key
     * @return
     */
    public static Map<String, Object> list2Map(List<Map<String, Object>> list, String key, String value)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        String paramKey = null;
        for (Map<String, Object> paramMap : list)
        {
            paramKey = ConverterUtils.toString(paramMap.get(key));
            if (!CheckUtils.isNullOrEmpty(paramKey))
            {
                map.put(paramKey, paramMap.get(value));
            }
        }
        return map;
    }

    /**
     *
     * <对map中存在key为null和不存在的key初始化空字符串>
     *
     * @param initMap
     * @param keys 需要的key们
     * @return
     */
    public static Map<String, Object> fillKeys(Map<String, Object> initMap, String... keys)
    {
        for (String key : keys)
        {
            if (!initMap.containsKey(key))
            {
                initMap.put(key, "");
                continue;
            }
            if (null == initMap.get(key))
            {
                initMap.put(key, "");
            }
        }
        return initMap;
    }

    /**
     *
     * <对map中存在key为null和不存在的key初始化0>
     *
     * @param initMap
     * @param keys 需要的key们
     * @return
     */
    public static Map<String, Object> fillIntegerKeys(Map<String, Object> initMap, String... keys)
    {
        for (String key : keys)
        {
            if (!initMap.containsKey(key))
            {
                initMap.put(key, 0);
                continue;
            }
            if (null == initMap.get(key))
            {
                initMap.put(key, 0);
            }
        }
        return initMap;
    }

    /**
     * 将一个list<bean>转换为一个 key为 bean id val为bean的map
     * @param list list
     * @param idField id字段名称
     * @return 见注释
     */
    public static <T> Map<Object,T> buildMapFormList(List<T> list,String idField)
    {

        Map<Object, T> resultMap = new HashMap<>();
        for(T temp : list )
        {
            resultMap.put(ReflectUtils.getValue(temp, idField), temp);
        }
        return resultMap;
    }


    /**
     * 把map转换为object
     * @param map map
     * @param beanClass  beanClass
     * @return
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        if (map == null) {
            return null;
        }
        Object obj = null;
        try {
            obj = beanClass.newInstance();
            Field[] fields = obj.getClass().getDeclaredFields();
            for (Field field : fields) {
                int mod = field.getModifiers();
                if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                    continue;
                }
                field.setAccessible(true);
                field.set(obj, map.get(field.getName()));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    /**
     * 把object转换为map
     * @param obj   obj
     * @return map
     */
    public static Map<String, Object> objectToMap(Object obj) {
        if(obj == null){
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 移除map中空key或者value空值
     * @param map
     */
    public static void removeNullEntry(Map<?, ?> map){
        removeNullKey(map);
        removeNullValue(map);
    }

    /**
     * 移除map的空key
     * @param map
     * @return
     */
    public static void removeNullKey(Map<?,?> map){
        Set<?> set = map.keySet();
        for (Iterator<?> iterator = set.iterator(); iterator.hasNext();) {
            Object obj = (Object) iterator.next();
            remove(obj, iterator);
        }
    }

    /**
     * 移除map中的value空值
     * @param map
     * @return
     */
    public static void removeNullValue(Map<?, ?> map){
        Set<?> set = map.keySet();
        for (Iterator<?> iterator = set.iterator(); iterator.hasNext();) {
            Object obj = (Object) iterator.next();
            Object value =(Object)map.get(obj);
            remove(value, iterator);
        }
    }

    /**
     * Iterator 是工作在一个独立的线程中，并且拥有一个 mutex 锁。
     * Iterator 被创建之后会建立一个指向原来对象的单链索引表，当原来的对象数量发生变化时，这个索引表的内容不会同步改变，
     * 所以当索引指针往后移动的时候就找不到要迭代的对象，所以按照 fail-fast 原则 Iterator 会马上抛出 java.util.ConcurrentModificationException 异常。
     * 所以 Iterator 在工作的时候是不允许被迭代的对象被改变的。
     * 但你可以使用 Iterator 本身的方法 remove() 来删除对象， Iterator.remove() 方法会在删除当前迭代对象的同时维护索引的一致性。
     * @param obj
     * @param iterator
     */
    private static void remove(Object obj,Iterator<?> iterator){
        if(obj instanceof String){
            String str = (String)obj;
            if(StringUtil.isEmpty(str)){
                iterator.remove();
            }
        }else if(obj instanceof Collection){
            Collection<?> col = (Collection<?>)obj;
            if(col==null||col.isEmpty()){
                iterator.remove();
            }

        }else if(obj instanceof Map){
            Map<?, ?> temp = (Map<?, ?>)obj;
            if(temp==null||temp.isEmpty()){
                iterator.remove();
            }

        }else if(obj instanceof Object[]){
            Object[] array =(Object[])obj;
            if(array==null||array.length<=0){
                iterator.remove();
            }
        }else{
            if(obj==null){
                iterator.remove();
            }
        }
    }
}
