package com.fhs.core.dodefault.aspect;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fhs.common.utils.CheckUtils;
import com.fhs.core.dodefault.annotation.DefaultVal;
import com.fhs.core.dodefault.sysdefault.ISysDefaultHandel;

/**
 * 对象属性默认值Aspect设定
 * 
 * @author jianbo.qin
 *
 */
@Aspect
@Component
public class DefaultValAspect
{
    private static Logger LOG = LoggerFactory.getLogger(DefaultValAspect.class);
    
    /**
     * 拦截所有service包
     */
    @Pointcut("execution(* *..service*..*(..)))")
    public void aspect()
    {
    }
    
    /**
     * 执行通知
     * 
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("aspect()")
    public Object around(ProceedingJoinPoint joinPoint)
        throws Throwable
    {
        Object[] args = null;
        try
        {
            args = joinPoint.getArgs(); // 方法的参数
            for (int i = 0; args != null && i < args.length; i++)
            {
                Object objz = args[i];
                this.getFieldParam(objz);
            }
        }
        catch (Throwable e)
        {
            LOG.error("设定默认值错误  : {}" + e);
            throw new Exception(e);
        }
        return joinPoint.proceed();
    }
    
    /**
     * 获取参数参数
     * 
     * @param args
     * @return
     * @throws Exception
     */
    private void getFieldParam(Object args)
        throws Exception
    {
        if (args == null)
            return;
        Field[] fields = args.getClass().getDeclaredFields();// 获取方法参数（实体）的field
        for (int j = 0; j < fields.length; j++)
        {
            Field field = fields[j];
            // 获取方法参数（实体）的field上的注解DefaultVal
            DefaultVal defaultVal =
                field.getAnnotation(DefaultVal.class) != null ? field.getAnnotation(DefaultVal.class) : null;
            if (defaultVal != null)
            {
                this.changValue(defaultVal, field, args);
            }
        }
    }
    
    /**
     * 修改默认值
     * 
     * @param defaultVal
     * @param field
     * @param args
     * @throws Exception
     */
    public void changValue(DefaultVal defaultVal, Field field, Object args)
        throws Exception
    {
        field.setAccessible(true);
        Object fieldValue = field.get(args);
        // 当设定值默认值非空的时候不增加
        if (CheckUtils.isNotEmpty(fieldValue))
            return;
        ISysDefaultHandel sysDefault = null;
        // 在系统SYS中会有别的类型、
        if (DefaultVal.SourceEnum.SYS == defaultVal.source())
        {
            // 目前暂时日期类型、后续在添加。。。。
            if (DefaultVal.TypeEnum.DATE == defaultVal.type())
            {
                String pattern = defaultVal.pattern().getPattern();
                if (CheckUtils.isNotEmpty(pattern))
                {
                    sysDefault = defaultPatternHandelMap.get(defaultVal.type().name());
                    sysDefault.setDefaultVal(args, field, pattern);
                }
            }
            else
            {
                // 其他类型后续增加.....
            }
        }
        else if (DefaultVal.SourceEnum.INPUT == defaultVal.source())
        {// 手动输入默认值
            field.set(args, defaultVal.val());
        }
    }
    
    private static final Map<String, ISysDefaultHandel> defaultPatternHandelMap = new HashMap<>();
    
    public static void registerIDefaultPattern(String type, ISysDefaultHandel sysDefault)
    {
        defaultPatternHandelMap.put(type, sysDefault);
    }
    
}