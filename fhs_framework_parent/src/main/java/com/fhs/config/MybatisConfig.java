package com.fhs.config;

import com.mybatis.jpa.core.PersistentEnhancerScaner;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class MybatisConfig {

    @Value("${fhs.mybatis-jpa.entity-package}")
    private String entityPackage;

    @Value("${fhs.mybatis-jpa.mapper-package}")
    private String mapperPackage;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Bean
    public PersistentEnhancerScaner getPersistentEnhancerScaner(){
        PersistentEnhancerScaner scanner = new PersistentEnhancerScaner();
        scanner.setEntityPackage(entityPackage);
        scanner.setMapperPackage(mapperPackage);
        scanner.setSqlSessionFactory(sqlSessionFactory);
        return scanner;
    }

}
