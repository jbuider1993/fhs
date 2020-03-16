package com.fhs.pagex.common;

import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.EMap;
import com.fhs.pagex.context.PagexServletContext;
import org.beetl.core.Template;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;

public class BeetlUtil {

    private static BeetlGroupUtilConfiguration beetlGroupUtilConfiguration;

    /**
     * 渲染一个模板
     *
     * @param viewPath 模板路径
     * @return 渲染好的模板内容
     */
    public static String renderBeelt(String viewPath, Map<String, Object> parameterMap) {
        if (beetlGroupUtilConfiguration == null) {
            beetlGroupUtilConfiguration = SpringContextUtil.getBeanByName(BeetlGroupUtilConfiguration.class);
        }
        Template template = beetlGroupUtilConfiguration.getGroupTemplate().getTemplate(viewPath);
        for (String key : parameterMap.keySet()) {
            template.binding(key, parameterMap.get(key));
        }
        if(PagexServletContext.getRequest()!=null)
        {
            template.binding("parameter", getParameterMap(PagexServletContext.getRequest()));
        }
        return template.render();
    }



    private static EMap<String, Object> getParameterMap(HttpServletRequest request) {
        EMap<String, Object> resultMap = new EMap<String, Object>();
        Map<String, String[]> tempMap = request.getParameterMap();
        Set<String> keys = tempMap.keySet();
        for (String key : keys) {
            resultMap.put(key, request.getParameter(key));
        }

        return resultMap;
    }
}
