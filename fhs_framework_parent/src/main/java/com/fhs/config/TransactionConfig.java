package com.fhs.config;

import com.fhs.common.utils.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.Properties;

/**
 *  声明式事务
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.config
 * @ClassName: TxAdviceInterceptor
 * @Author: JackWang
 * @CreateDate: 2018/8/15 0015 16:09
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/8/15 0015 16:09
 * @Version: 1.0
 */
@Configuration
public class TransactionConfig {

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Value("${fhs.transcatoin.interceptors:}")
    private String transcationInterceptor;

    @Bean(name = "txAdvice")
    public TransactionInterceptor getAdvisor() throws Exception {
        Properties properties = new Properties();
        properties.setProperty("get*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("find*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("select*", "PROPAGATION_REQUIRED,-Exception,readOnly");
        properties.setProperty("insert*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("add*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("save*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("create*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("update*", "PROPAGATION_REQUIRED,-Exception");
        properties.setProperty("del*", "PROPAGATION_REQUIRED,-Exception");
        TransactionInterceptor result = new TransactionInterceptor(transactionManager, properties);
        return result;

    }

    @Bean(name="myBeanNameAutoProxyCreator")
    public MyBeanNameAutoProxyCreator txProxy() {
        MyBeanNameAutoProxyCreator creator = new MyBeanNameAutoProxyCreator();
        if(CheckUtils.isNullOrEmpty(transcationInterceptor))
        {
            creator.setInterceptorNames(new String[]{"txAdvice"});
        }
        else
        {
            String[] interceptorNamesSett = transcationInterceptor.split(",");
            String [] interceptorNames = new String[interceptorNamesSett.length + 1];
            int i = 0;
            for(String name:interceptorNamesSett)
            {
                interceptorNames[i++] = name;
            }
            interceptorNames[interceptorNamesSett.length] = "txAdvice";
            creator.setInterceptorNames(interceptorNames);
        }


        creator.setProxyTargetClass(true);
        return creator;
    }


}