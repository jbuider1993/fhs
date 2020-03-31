package com.fhs.module.base.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

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
@EnableSwagger2
@Configuration
@ConditionalOnProperty(prefix = "fhs.swagger", name = "enable", havingValue = "true", matchIfMissing = false)
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
    protected Environment environment;
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
    @Override
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

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.basePackage = this.environment.getProperty("fhs.swagger.basepackage");
        this.creatName = this.environment.getProperty("fhs.swagger.service.developer");
        this.serviceName = this.environment.getProperty("fhs.swagger.service.name");
        this.description = this.environment.getProperty("fhs.swagger.service.description");
    }

}