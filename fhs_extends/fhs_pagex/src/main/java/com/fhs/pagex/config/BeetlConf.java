package com.fhs.pagex.config;

import com.fhs.common.utils.EMap;
import com.fhs.core.base.controller.BaseController;
import org.beetl.core.Template;
import org.beetl.core.resource.ClasspathResourceLoader;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.beetl.ext.spring.BeetlSpringViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * beetls 集成
 * by jackwong
 */
@Configuration
@Controller
@Component
public class BeetlConf extends BaseController {

    /**
     * 自身
     */
    public static BeetlConf beetlConf;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private BeetlGroupUtilConfiguration beetlGroupUtilConfiguration;

    @Bean(name = "beetlConfig")
    public BeetlGroupUtilConfiguration getBeetlGroupUtilConfiguration() {
        BeetlGroupUtilConfiguration beetlGroupUtilConfiguration = new BeetlGroupUtilConfiguration();
        ClasspathResourceLoader classpathResourceLoader = new ClasspathResourceLoader();
        beetlGroupUtilConfiguration.setResourceLoader(classpathResourceLoader);
        beetlGroupUtilConfiguration.init();
        this.beetlGroupUtilConfiguration = beetlGroupUtilConfiguration;
        return beetlGroupUtilConfiguration;
    }

    @Bean(name = "beetlViewResolver")
    public BeetlSpringViewResolver getBeetlSpringViewResolver(
            @Qualifier("beetlConfig") BeetlGroupUtilConfiguration beetlGroupUtilConfiguration,ContentNegotiatingViewResolver
            contentNegotiatingViewResolver) {
        BeetlSpringViewResolver beetlSpringViewResolver = new BeetlSpringViewResolver();
        beetlSpringViewResolver.setSuffix(".html");
        beetlSpringViewResolver.setContentType("text/html;charset=UTF-8");
        beetlSpringViewResolver.setOrder(0);
        beetlSpringViewResolver.setCache(false);
        beetlSpringViewResolver.setConfig(beetlGroupUtilConfiguration);
        contentNegotiatingViewResolver.setOrder(1);
        beetlConf = this;
        return beetlSpringViewResolver;
    }

    /**
     * 渲染beetls 模板
     * @param dir 路径
     * @param fileName 文件名称
     * @return ModelAndView 对象
     */
    @RequestMapping("/b/{dir}/{fileName}")
    public ModelAndView renderBeelts(@PathVariable("dir") String dir,@PathVariable("fileName")String fileName){
        ModelAndView view = new ModelAndView();
        view.setViewName(dir.replace("-","/") + "/" + fileName);
        return view;
    }


    /**
     * 渲染一个模板
     * @param viewPath 模板路径
     * @param request  request
     * @return 渲染好的模板内容
     */
    public String renderBeelt(String viewPath, HttpServletRequest request)
    {
        Template template = beetlGroupUtilConfiguration.getGroupTemplate().getTemplate(viewPath);
        EMap<String, Object> parameterMap = super.getParameterMap();
        template.binding("parameter",parameterMap);
        return template.render();
    }

    /**
     * 渲染一个模板
     * @param viewPath 模板路径
     * @return 渲染好的模板内容
     */
    public String renderBeelt(String viewPath,  Map<String,Object> parameterMap)
    {
        Template template = beetlGroupUtilConfiguration.getGroupTemplate().getTemplate(viewPath);
        for(String key : parameterMap.keySet())
        {
            template.binding(key,parameterMap.get(key));
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();
        template.binding("parameter",super.getParameterMap());
        return template.render();
    }
}