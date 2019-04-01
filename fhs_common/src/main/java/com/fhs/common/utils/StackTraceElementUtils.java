package com.fhs.common.utils;

import com.fhs.common.constant.Constant;

/**
 * 堆栈信息工具类
 * @author  wanglei
 * @version  [版本号, 2016年12月30日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class StackTraceElementUtils
{
    /**
     * 开始从第二层级开始
     */
    private static final int START = 2;

    /**
     * 获取堆栈信息字符串
     * @return 堆栈信息字符串
     */
    public static String getStackStr()
    {
        return getStackStr(0);
    }


    /**
     * 获取堆栈信息字符串
     * @param maxNum 最大层级
     * @return 堆栈信息字符串
     */
    public static String getStackStr(int maxNum)
    {
        StringBuilder resultSb = new StringBuilder();
        StackTraceElement[] stackTraceArray = Thread.currentThread().getStackTrace();
        StackTraceElement stackTrace = null;
        if(maxNum == Constant.ZERO)
        {
            maxNum = stackTraceArray.length;
        }
        else
        {
            maxNum = maxNum + START;
        }
        try {
            for(int i = START;i < maxNum;i++)
            {
                stackTrace = stackTraceArray[i];
                resultSb.append("className:" + stackTrace.getClassName() + "." + stackTrace.getMethodName() + " function the lineNum is:" + stackTrace.getLineNumber()+ "\n");
            }
        }catch(Exception e)
        {

        }

        return resultSb.toString();
    }
}
