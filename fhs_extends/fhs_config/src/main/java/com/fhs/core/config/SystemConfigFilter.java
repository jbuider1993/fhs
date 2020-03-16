package com.fhs.core.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.fhs.common.utils.ConverterUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 如果请求 的是systemConfig.js 则读取配置文件给浏览器写
 * @author jianbo.qin
 *
 */
@Configuration
@WebFilter(urlPatterns = "/*",filterName = "systemConfigFilter")
@Component
public class SystemConfigFilter implements Filter, InitializingBean
{
    public  static String projectName = EConfig.projectName;

    private static String fileName = "systemConfig.js";

    /**
     * systemconfig js内容
     */
    private static String jsContent;
    
    public static String basePath;

    @ApolloConfig("jsConfig")
    private Config systemConfig;

    @Value("${fhs.disable-apollo:false}")
    private boolean isDisableApollo;

    @Override
    public void destroy()
    {
        
    }
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        HttpServletResponse httpServletResponse = (HttpServletResponse)response;
        if (httpServletRequest.getRequestURI().contains(fileName))
        {
            httpServletResponse.getWriter().println(jsContent);
            httpServletResponse.getWriter().flush();
            httpServletResponse.getWriter().close();
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    
    public void init(FilterConfig FilterConfig)
        throws ServletException
    {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,String> systemConfigMap = new HashMap<>();
        if(isDisableApollo)
        {
            Properties jsConfig = new Properties();
            InputStream in = null;
            File jsFile = new File(EConfig.getPath() + "/js.properties");
            if(jsFile.exists())
            {
                in = new FileInputStream(jsFile);
            }
            else{
                in = SystemConfigFilter.class.getClassLoader().getResourceAsStream("js.properties");
            }

            jsConfig.load(in);
            for(Object key : jsConfig.keySet())
            {
                systemConfigMap.put(ConverterUtils.toString(key),ConverterUtils.toString(jsConfig.get(key)));
            }
        }
        else
        {
            for(String key : systemConfig.getPropertyNames())
            {
                systemConfigMap.put(key,systemConfig.getProperty(key,""));
            }
        }
        final StringBuilder jsBuilder = new StringBuilder();
        jsBuilder.append("var basePath" + " = '" +  EConfig.getPathPropertiesValue("basePath") + "';");
        systemConfigMap.keySet().forEach(name->{
            if(name.endsWith("_REG"))
            {
                jsBuilder.append("var " + name + " = " +  systemConfigMap.get(name)+";");
            }
            else {
                jsBuilder.append("var " + name + " = '" +  systemConfigMap.get(name)+"';");
            }
        });
        jsContent =jsBuilder.toString();
    }
}
