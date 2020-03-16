package com.fhs.common.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  提供强制转换的map
 *  本来继承与hashmap
 * @author  jackwong
 * @version  [版本号, 2016年12月12日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class EMap<K, V> extends HashMap<K, V>
{
    /**
     *serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 将一个普通的map转换为EMap
     * @param map 普通的map
     * @return Emap
     */
    public  EMap<K, V> buildEmap(Map<K,V> map)
    {
        this.putAll(map);
        return this;
    }

    /**
     * 获取一个map用来给原来框架的base类传参数
     * @return  EMap<String,Object> 对象，内容和原来的相同
     */
    public EMap<String,Object> getEmapForParam()
    {
        EMap<String,Object> tempEmap = new EMap<String,Object>();
        Set<K> keys = this.keySet();
        for(K key : keys)
        {
            tempEmap.put(key.toString(), this.get(key));
        }
        return tempEmap;
    }


    /**
     * 获取字符串
     * @param key key
     * @return key对应的对象的string类型
     */
    public String getStr(Object key)
    {
        return StringUtil.toString(super.get(key));
    }

    /**
     * 获取字符串
     * @param key key
     * @param defaultVal 默认值
     * @return key对应的对象的string类型
     */
    public String getStr(Object key,String defaultVal)
    {
        return StringUtil.toString(super.get(key),defaultVal);
    }

    /**
     * 获取int
     * @param key key
     * @return key对应的对象的string类型
     */
    public Integer getInteger(Object key)
    {
        return ConverterUtils.toInt(super.get(key));
    }

    /**
     * 穿入一个key返回一个double val
     * @param key key
     * @return val 如果val为空或者不能转换为double 则返回0
     */
    public double  getDouble(Object key)
    {
        return ConverterUtils.toDouble(super.get(key));
    }

    /**
     * 获取int
     * @param key key
     * @param defaultVal 默认值
     * @return key对应的对象的string类型
     */
    public Integer getInteger(Object key,int defaultVal)
    {
        return ConverterUtils.toInt(super.get(key),defaultVal);
    }

    /**
     * 将本map转换位一个bean
     * @param cls class
     * @return bean对象
     */
    public <T> T asBean(Class<T> cls)
    {
        return MapUtils.map2Bean(this, cls);
    }

    /**
     * 将本map转换为一个json
     * @return json
     */
    public String asJson()
    {
        return JsonUtils.map2json(this);
    }
}
