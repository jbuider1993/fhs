package com.fhs.config;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.fhs.common.utils.Logger;
import com.mybatis.jpa.plugin.MultiTenancyPlugin;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis 为了支持saas的配置
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
public class SaasMybatisConfig implements InitializingBean {

    private static Logger LOG =  Logger.getLogger(SaasMybatisConfig.class);

    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    @Override
    public void afterPropertiesSet() throws Exception {
        //多租户拦截器
        MultiTenancyPlugin multiTenancyInterceptor = new MultiTenancyPlugin();
        multiTenancyInterceptor.setProviderIdField("group_code");
        sqlSessionFactory.getConfiguration().addInterceptor(multiTenancyInterceptor);
    }
}
