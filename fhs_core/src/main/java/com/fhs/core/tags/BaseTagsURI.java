package com.fhs.core.tags;

import com.fhs.core.config.EConfig;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 *
 * 系统初始化获取 获取tags中url信息
 *
 * @author jianbo.qin
 *
 */
@Configuration
@Component
public class BaseTagsURI implements InitializingBean
{

    @Autowired
    private EConfig config;

    /**
     * 基础服务url
     * 字典，省市区用此url
     */
    public static String systemServiceUrl;

    /**
     * 静态资源url
     */

    public static String staticPath;

    /**
     * 项目路径
     */
    public static String basePath;

    @Override
    public void afterPropertiesSet() throws Exception {
        systemServiceUrl = EConfig.getPathPropertiesValue("systemServiceUrl");
        staticPath = EConfig.getPathPropertiesValue("staticPath");
        basePath = EConfig.getPathPropertiesValue("basePath");

    }
}
