package com.fhs.shiro;

import com.fhs.common.utils.Logger;
import org.apache.shiro.SecurityUtils;
import org.beetl.core.Tag;
import org.beetl.ext.spring.BeetlGroupUtilConfiguration;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 为shiro 添加beetls模板引擎适配
 */
@Component
public class ShiroBeetlTag extends Tag implements InitializingBean {

    private static final Logger LOGGER = Logger.getLogger(ShiroBeetlTag.class);

    @Autowired
    private BeetlGroupUtilConfiguration beetlGroupUtilConfiguration;

    @Override
    public void render(){
        String tagName = (String) this.args[0];
        Map attrs = (Map) args[1];
        String name = (String) attrs.get("name");
        //如果有权限就渲染，没权限就忽略
        if(SecurityUtils.getSubject().isPermitted(name))
        {
            this.doBodyRender();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        beetlGroupUtilConfiguration.getGroupTemplate().registerTag("shiro",ShiroBeetlTag.class);
    }
}