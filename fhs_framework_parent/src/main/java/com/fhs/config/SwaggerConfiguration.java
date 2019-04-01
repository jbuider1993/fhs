package com.fhs.config;

import com.google.common.base.Predicate;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.base.action
 * @ClassName: SwaggerConfiguration
 * @Author: JackWang
 * @CreateDate: 2018/9/9 0009 14:52
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/9/9 0009 14:52
 * @Version: 1.0
 */
@Configuration
//@EnableSwagger2
public class SwaggerConfiguration extends WebMvcConfigurerAdapter implements EnvironmentAware {
    /**
     * 基础包
     */
    private String basePackage;
    /**
     * 创建人
     */
    private String creatName;
    /**
     * 服务名称
     */
    private String serviceName;
    private RelaxedPropertyResolver propertyResolver;
    /**
     * 文档描述
     */
    private String description;

    public SwaggerConfiguration() {
    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"swagger-ui.html"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/"});
        registry.addResourceHandler(new String[]{"/webjars*"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"});
    }

    @Bean
    public Docket createRestApi() {
        return (new Docket(DocumentationType.SWAGGER_2)).apiInfo(this.apiInfo()).select().
                apis(RequestHandlerSelectors.basePackage(this.basePackage))
                .paths(pathRegex()).build();
    }

    /**
     * 根据路径过滤
     * @return
     */
    public static Predicate<String> pathRegex() {
        return new Predicate<String>() {
            @Override
            public boolean apply(String input) {
                return input.startsWith("/webApi/") || input.startsWith("/api/")  || input.startsWith("/webapi/");
            }
        };
    }

    private ApiInfo apiInfo() {
        return (new ApiInfoBuilder()).title(this.serviceName + " Restful APIs").description(this.description).contact(this.creatName).version("1.0").build();
    }

    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, (String)null);
        this.basePackage = this.propertyResolver.getProperty("swagger.basepackage");
        this.creatName = this.propertyResolver.getProperty("swagger.service.developer");
        this.serviceName = this.propertyResolver.getProperty("swagger.service.name");
        this.description = this.propertyResolver.getProperty("swagger.service.description");
    }

}