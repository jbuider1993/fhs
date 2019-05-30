package com.fhs.pagex.common;

import com.fhs.common.utils.EMap;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

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
     * 获取参数map
     * @param request request
     * @return 参数map
     */
    public static EMap<String, Object> getParameterMap(HttpServletRequest request) {
        EMap<String, Object> resultMap = new EMap();
        Map<String, String[]> tempMap = request.getParameterMap();
        Set<String> keys = tempMap.keySet();
        Iterator var5 = keys.iterator();

        while(var5.hasNext()) {
            String key = (String)var5.next();
            resultMap.put(key, request.getParameter(key));
        }

        return resultMap;
    }
}
