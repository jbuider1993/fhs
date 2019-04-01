package com.fhs.core.base.bean;

import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.MapUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class BaseObject<T extends BaseObject> implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * 重载方法
     *
     * @return json格式
     */
    public String toString()
    {
        return JsonUtils.bean2json(this);
    }

    /**
     * 如果要调用此参数
     *
     * @param args
     * @return
     */
    @SuppressWarnings("unchecked")
    public T mk(Object... args)
    {
        Map<? extends Object, Object> paramMap = MapUtils.getMapFromArgs(args);
        // 取出bean里的所有方法
        Method[] methods = this.getClass().getMethods();
        for (int i = 0; i < methods.length; i++)
        {
            // 取方法名
            String method = methods[i].getName();
            // 取出方法的类型
            Class[] cc = methods[i].getParameterTypes();
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
                if (paramMap.containsKey(value) && paramMap.get(value) != null)
                {
                    // 调用其底层方法
                    MapUtils.setValue(type, paramMap.get(value), i, methods, this);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return (T)this;
    }

    /**
     * 将对象转换为map
     *
     * @return map
     */
    public Map<String, Object> asMap()
    {
        return MapUtils.bean2Map(this);
    }

    /**
     * 将对象转换为json
     *
     * @return
     */
    public String asJson()
    {
        return JsonUtils.bean2json(this);
    }

}

