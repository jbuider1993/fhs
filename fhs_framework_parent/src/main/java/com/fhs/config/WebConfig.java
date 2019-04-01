package com.fhs.config;

import com.fhs.common.utils.Logger;
import com.fhs.core.safe.csrf.CSRFFileter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * tomcat配置
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter implements EnvironmentAware
{

    /*
     *日志
     */
    private static final Logger LOGGER = Logger.getLogger(WebConfig.class);

    @Value("${fhs.session.timeout}")
    private int sessionTimeout;

    private RelaxedPropertyResolver propertyResolver;


    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false).
                setUseTrailingSlashMatch(true);
    }

    public void setEnvironment(Environment environment) {
        this.propertyResolver = new RelaxedPropertyResolver(environment, (String)null);
    }

    /**
     * 修改session过期时间
     * @return
     */
     @Bean
     public EmbeddedServletContainerCustomizer containerCustomizer(){
          return new EmbeddedServletContainerCustomizer() {
                  @Override
                  public void customize(ConfigurableEmbeddedServletContainer container) {
                  container.setSessionTimeout(sessionTimeout);//单位为S 半个小时过期
                  }
            };
       }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       /* registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
        registry.addResourceHandler("/fount/**").addResourceLocations("classpath:/fount/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/images/");
        registry.addResourceHandler("/artIndex/**").addResourceLocations("classpath:/artIndex/");*/
        super.addResourceHandlers(registry);
    }

    /**
     *csrf漏洞修复过滤器
     */
    @Bean
    public FilterRegistrationBean csrfFileter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setName("csrfFileter");
        bean.setFilter(new CSRFFileter());
        bean.addUrlPatterns("/ms/*","/page/ms/*");
        bean.setEnabled(true);
        return bean;
    }

}
