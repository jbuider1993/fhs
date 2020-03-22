package com.fhs.module.base.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusProperties;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.config.EConfig;
import com.fhs.logger.Logger;
import com.mybatis.jpa.core.PersistentEnhancerScaner;
import com.mybatis.jpa.plugin.ResultTypePlugin;
import com.mybatis.jpa.xml.XMLMapperLoader;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis jpa 配置
 *
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.config
 * @ClassName: MybatisConfig
 * @Author: JackWang
 * @CreateDate: 2018/8/15 0015 16:04
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/8/15 0015 16:04
 * @Version: 1.0
 */
@Configuration
@AutoConfigureAfter(MybatisPlusAutoConfiguration.class)
public class MybatisConfig implements InitializingBean {

    private static Logger LOG =  Logger.getLogger(MybatisConfig.class);

    @Value("${fhs.mybatis-jpa.entity-package}")
    private String entityPackage;

    @Value("${fhs.mybatis-jpa.mapper-package}")
    private String mapperPackage;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Bean("persistentEnhancerScaner")
    public PersistentEnhancerScaner getPersistentEnhancerScaner(){
        PersistentEnhancerScaner scanner = new PersistentEnhancerScaner();
        scanner.setEntityPackage(entityPackage);
        scanner.setMapperPackage(mapperPackage);
        scanner.setSqlSessionFactory(sqlSessionFactory);
        return scanner;
    }


    @Bean
    public XMLMapperLoader getXMLMapperLoader(MybatisPlusProperties plusProperties){
        XMLMapperLoader loader = new XMLMapperLoader();
        loader.setEnabled(ConverterUtils.toBoolean(EConfig.getOtherConfigPropertiesValue("isDevModel")));//开启xml热加载
        loader.setMapperLocations(plusProperties.resolveMapperLocations());
        LOG.info("xml刷新器初始化:" + plusProperties.getMapperLocations() + "--" + loader.getMapperLocations());
        return loader;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Interceptor zipkinInterceptor = new ResultTypePlugin();
        sqlSessionFactory.getConfiguration().addInterceptor(zipkinInterceptor);
    }
}
