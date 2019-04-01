/*
package com.fhs.beetl;

import com.fhs.core.base.action.BaseAction;
import org.beetl.core.Template;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

*/
/**
 * beetl 前端模板渲染
 *//*

@Controller
public class BeetlRenderAction extends BaseAction {

    @Autowired
    private BeetlGroupUtilConfiguration beetlGroupUtilConfiguration;

    */
/**
     * 渲染beetls 模板
     * @param dir 路径
     * @param fileName 文件名称
     * @return ModelAndView 对象
     *//*

    @RequestMapping("/b/{dir}/{fileName}")
    public ModelAndView renderBeelts(@PathVariable("dir") String dir, @PathVariable("fileName")String fileName){
        ModelAndView view = new ModelAndView();
        view.setViewName(dir.replace("-","/") + "/" + fileName);
        return view;
    }


    */
/**
     * 渲染一个模板
     * @param viewPath 模板路径
     * @param request  request
     * @return 渲染好的模板内容
     *//*

    public String renderBeelt(String viewPath, HttpServletRequest request)
    {
        Template template = beetlGroupUtilConfiguration.getGroupTemplate().getTemplate(viewPath);
        Map<String,String> parameterMap = super.getParameterMap(request);
        template.binding("parameter",parameterMap);
        return template.render();
    }

    */
/**
     * 渲染一个模板
     * @param viewPath 模板路径
     * @return 渲染好的模板内容
     *//*

    public String renderBeelt(String viewPath,  Map<String,Object> parameterMap)
    {
        Template template = beetlGroupUtilConfiguration.getGroupTemplate().getTemplate(viewPath);
        for(String key : parameterMap.keySet())
        {
            template.binding(key,parameterMap.get(key));
        }
        return template.render();
    }
}
*/
