package com.fhs.config;

import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

/**
 * swagger配置
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.base.action
 * @ClassName: SwaggerConfiguration
 * @Author: JackWang
 * @CreateDate: 2018/9/9 0009 14:52
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/9/9 0009 14:52
 * @Version: 1.0
 */

public class SwaggerConfiguration extends WebMvcConfigurerAdapter implements EnvironmentAware {
    /**
     * 基础包
     */
    protected String basePackage;
    /**
     * 创建人
     */
    protected String creatName;
    /**
     * 服务名称
     */
    protected String serviceName;

    /**
     * 配置文件获取
     */
    protected RelaxedPropertyResolver propertyResolver;
    /**
     * 文档描述
     */
    protected String description;

    public SwaggerConfiguration() {
    }

    /**
     * 白名单
     *
     * @param registry
     */
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"swagger-ui.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"/webjars*"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});
    }


    /**
     * api信息
     *
     * @return
     */
    protected ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title(this.serviceName + " Restful APIs").description(this.description).contact(this.creatName).version("1.0").build();
    }

    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, (String) null);
        this.basePackage = this.propertyResolver.getProperty("swagger.basepackage");
        this.creatName = this.propertyResolver.getProperty("swagger.service.developer");
        this.serviceName = this.propertyResolver.getProperty("swagger.service.name");
        this.description = this.propertyResolver.getProperty("swagger.service.description");
    }

}