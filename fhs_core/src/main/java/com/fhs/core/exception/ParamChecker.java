package com.fhs.core.exception;

import com.fhs.common.utils.CheckUtils;

/**
 * 用来检查参数的
 */
public class ParamChecker {

    /**
     * 如果为空返回paramexception
     * @param obj 字符串
     * @param msg 消息
     */
    public static void isNotNullOrEmpty(Object obj,String msg){
        if(CheckUtils.isNullOrEmpty(obj))
        {
            throw  new ParamException(msg);
        }
    }

    /**
     * 如果为空返回paramexception
     * @param obj 对象
     * @param msg 消息
     */
    public static void isNotNull(Object obj,String msg){
        if(obj == null)
        {
            throw  new ParamException(msg);
        }
    }
}
