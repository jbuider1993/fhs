package com.fhs.core.strategy.aspect;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fhs.common.utils.CheckUtils;
import com.fhs.core.exception.ParamException;
import com.fhs.core.strategy.IStrategy;
import com.fhs.core.strategy.enume.GeneratedType;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.persistence.GeneratedValue;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 主键生成策略
 * 
 * @author jianbo.qin
 *
 */
@Aspect
@Component
public class GeneratedValueAspect
{
    private static Logger LOG = LoggerFactory.getLogger(GeneratedValueAspect.class);

    /**
     * mp注解和mybatis jpa注解转换
     */
    private static Map<IdType,String> MP_2_MJ = new HashMap<>();

    static
    {
        MP_2_MJ.put(IdType.UUID, GeneratedType.UUID);
    }

    /**
     * 类和类里面需要设置主键的字段缓存
     */
    private static Map<Class, Field> GENERATED_VALUE_FIELD_CACHE = new HashMap<>();

    /**
     *strategyMap
     */
    private static final Map<String, IStrategy> STRATEGY_MAP = new HashMap<>();
    
    /**
     * 拦截所有service包
     */
    @Pointcut("@annotation(com.fhs.core.strategy.GenInfo)")
    public void aspect()
    {
        LOG.info("aspect begin...");
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
            if(args != null && args.length > 0) {
                Object objz = args[0];
                this.getField(objz);
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
    private void getField(Object args)
        throws Exception
    {
        if(GENERATED_VALUE_FIELD_CACHE.containsKey(args.getClass())
                &&(GENERATED_VALUE_FIELD_CACHE.get(args.getClass())==null))
        {
            return;
        }
        Field pkeyField = null;
        if(GENERATED_VALUE_FIELD_CACHE.containsKey(args.getClass()))
        {
             pkeyField = GENERATED_VALUE_FIELD_CACHE.get(args.getClass());
            if (pkeyField.isAnnotationPresent(GeneratedValue.class))
            {
                GeneratedValue generatedValue =  pkeyField.getAnnotation(GeneratedValue.class);
                this.changValue(generatedValue.generator(), pkeyField, args);
            }
            else if(pkeyField.isAnnotationPresent(TableId.class))
            {
                TableId tableId = pkeyField.getAnnotation(TableId.class);
                this.changValue(MP_2_MJ.get(tableId.type()), pkeyField, args);
            }
        }
        Field[] fields = args.getClass().getDeclaredFields();// 获取方法参数（实体）的field
        boolean isHashAnnotation = false;
        for (int j = 0; j < fields.length; j++)
        {
             pkeyField = fields[j];
            // 获取方法参数（实体）的field上的注解GeneratedValue
            if (pkeyField.isAnnotationPresent(GeneratedValue.class))
            {
                GeneratedValue generatedValue =  pkeyField.getAnnotation(GeneratedValue.class);
                this.changValue(generatedValue.generator(), pkeyField, args);
                isHashAnnotation = true;
                break;
            }
            else if(pkeyField.isAnnotationPresent(TableId.class))
            {
                TableId tableId = pkeyField.getAnnotation(TableId.class);
                this.changValue(MP_2_MJ.get(tableId.type()), pkeyField, args);
                isHashAnnotation = true;
                break;
            }
        }
        if(!isHashAnnotation)
        {
            GENERATED_VALUE_FIELD_CACHE.put(args.getClass(),null);
            return;
        }
        GENERATED_VALUE_FIELD_CACHE.put(args.getClass(),pkeyField);
    }
    
    /**
     * 修改默认值
     * 
     * @param field
     * @param args
     * @throws Exception
     */
    public void changValue(String genType, Field field, Object args)
        throws Exception
    {
        field.setAccessible(true);
        Object fieldValue = field.get(args);
        // 当设定值默认值非空的时候不增加
        if (CheckUtils.isNotEmpty(fieldValue))
            return;
        if(!STRATEGY_MAP.containsKey(genType))
        {
            throw new ParamException("主键生成器类型错误:"+genType);
        }
    }


    /**
     * 注册主键生成器
     * @param type 主键类型
     * @param strategy 主键生成器
     */
    public static void registerIStrategy(String type, IStrategy strategy)
    {
        STRATEGY_MAP.put(type, strategy);
    }
    
}