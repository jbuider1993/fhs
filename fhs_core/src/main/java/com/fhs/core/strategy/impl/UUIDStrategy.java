package com.fhs.core.strategy.impl;

import com.fhs.common.utils.StringUtil;
import com.fhs.core.strategy.IStrategy;
import com.fhs.core.strategy.aspect.GeneratedValueAspect;
import com.fhs.core.strategy.enume.GeneratedType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * UUID生成实现
 * 
 * @author jianbo.qin
 *
 */
@Component
public class UUIDStrategy implements IStrategy,InitializingBean
{
    
    private static Logger LOG = LoggerFactory.getLogger(UUIDStrategy.class);
    
    /**
     * order key
     */
    private static final String key = GeneratedType.UUID;
    

    
    @Override
    public void afterPropertiesSet()
        throws Exception
    {
        GeneratedValueAspect.registerIStrategy(key, this);
    }

    @Override
    public Object getPkey(String className) {
        return StringUtil.getUUID();
    }
}
