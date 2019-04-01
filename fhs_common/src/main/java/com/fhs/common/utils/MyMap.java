package com.fhs.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置文件properties读取
 * @author  xiaofu
 * @version  [版本号, 2015年8月3日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MyMap<S,T>
{
    List<S> keyList= new ArrayList<S>();
    List<T> valList = new ArrayList<T>();

    public void  put(S key,T value)
    {
        int index = findIndex(key);
        if(index==-1)
        {
            keyList.add(key);
            valList.add(value);
        }else{
            valList.set(index, value);

        }
    }
    private int findIndex(S key){
        for(int i = 0;i<keyList.size();i++){
            S item = keyList.get(i);
            if(item.equals(key))
            {
                return i;
            }
        }
        return -1;
    }

    public T get(S key){
        int index = findIndex(key);
        if(index==-1)
        {
            return null;
        };
        return valList.get(index);
    }

    public List<S> getKeyList(){
        return this.keyList;
    }
    public List<T> getValueList(){
        return this.valList;
    }
}
