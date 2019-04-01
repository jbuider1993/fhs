package com.fhs.common.utils;

import com.fhs.common.constant.Constant;

import java.util.Map;

/**
 * 主要用于分页
 * @author  wanglei
 * @version  [版本号, 2016年10月29日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class PageUtils
{
    /** 
     * 初始化start和end两个分页参数
     * @param paramMap 参数map主要包含page和 pagesize两个参数
     * @return 包含start和end的参数map
     */
    public static Map<String,Object> initPagerInfo(Map<String,Object> paramMap)
    {
        int page = ConverterUtils.toInt(paramMap.get(Constant.PAGE));
        int rows = ConverterUtils.toInt(paramMap.get(Constant.PAGE_SIZE));
        if (page == Constant.ZERO || rows == Constant.ZERO)
        {
            // 默认返回10条数据
            paramMap.put(Constant.START, 0);
            paramMap.put(Constant.END, 10);
        }
        else
        {
            paramMap.put(Constant.START, ConverterUtils.toString((page - 1) * rows));
            paramMap.put(Constant.END, ConverterUtils.toString(rows));
        }
        return paramMap;
    }
    
    
}
