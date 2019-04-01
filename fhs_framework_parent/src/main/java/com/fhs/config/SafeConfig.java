package com.fhs.config;

import com.fhs.core.safe.spring.XSSStringEditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

/**
 * 安全配置
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.config
 * @ClassName: SafeConfig
 * @Author: JackWang
 * @CreateDate: 2018/12/9 0009 14:15
 * @UpdateUser: JackWang
 * @UpdateDate: 2018/12/9 0009 14:15
 * @Version: 1.0
 */
@Configuration
public class SafeConfig {

    @Value("${fhs.safe.enable-xss}")
    private boolean enableXSS;
    @Autowired
    public void setWebBindingInitializer(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        if(enableXSS)
        {
            requestMappingHandlerAdapter.setWebBindingInitializer(new XSSStringEditor());
        }
    }

}
