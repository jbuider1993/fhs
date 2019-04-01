package com.fhs.core.dodefault.sysdefault;

import java.lang.reflect.Field;

import com.fhs.core.dodefault.aspect.DefaultValAspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.fhs.common.utils.DateUtils;
import com.fhs.core.dodefault.annotation.DefaultVal;

@Component
public class DateDefault implements ISysDefaultHandel, InitializingBean
{
    private static Logger LOG = LoggerFactory.getLogger(DateDefault.class);
    
    public static enum Date
    {
        
        DATETIME_PATTERN("yyyy-MM-dd HH:mm:ss"),
        
        DATETIME_PATTERN_NO_MSE("yyyy-MM-dd HH:mm"),
        
        DATETIME_PATTERN_DATE("yyyy-MM-dd"),
        
        DATETIME_PATTERN_DATE_NO_("yyyyMMdd"),
        
        DATE_FULL_STR_SSS("yyyy-MM-dd HH:mm:ss SSS"),
        
        DATE_FULL_SS_NO_SP("yyyyMMddHHmmss"),
        
        DATETIME_NO_HOUR("yyyyMMdd"),
        
        DATETIME_YEAR("yyyy"),
        
        DATETIME_MONTH("MM");
        
        private String pattern;
        
        Date(String pattern)
        {
            this.pattern = pattern;
        }
        
        public String getPattern()
        {
            return pattern;
        }
        
    }
    
    @Override
    public void setDefaultVal(Object obj, Field field, String... pattern)
    {
        try
        {
            for (String string : pattern)
            {
                field.set(obj, DateUtils.getCurrentDateStr(string));
            }
        }
        catch (Exception e)
        {
            LOG.error("日期格式化错误 : {}", e);
        }
    }
    
    @Override
    public void afterPropertiesSet()
        throws Exception
    {
        DefaultValAspect.registerIDefaultPattern(DefaultVal.TypeEnum.DATE.name(), this);
    }
    
}
