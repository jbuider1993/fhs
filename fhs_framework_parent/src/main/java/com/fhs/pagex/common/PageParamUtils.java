package com.fhs.pagex.common;

import java.util.Map;

/**
 *  页面参数处理
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.pagex.common
 * @ClassName: PageParamUtils
 * @Author: JackWang
 * @CreateDate: 2018/11/30 0030 22:32
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/11/30 0030 22:32
 * @Version: 1.0
 */
public class PageParamUtils {

    /**
     * 把一些自己没有专门处理的 属性拼接起来
     * 比如param中有10个属性 其中有五个没指定处理方式
     * 这五个会被 拼接为 key="val"
     * @param param 需要处理的参数
     * @param appointArgs 指定自己用其他代码处理的参数
     * @return 给拼成川的参数
     */
    public static String parseOtherAttr(Map<String,Object> param, String ... appointArgs)
    {
        return null;
    }
}
