package com.fhs.core.valid.aop;

import com.fhs.common.utils.JsonUtils;
import com.fhs.core.result.HttpResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.context.request.async.DeferredResult;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * 参数校验aop
 * @author qh
 */
public abstract class ParamCheckAop {

    private static Properties messageProperties  = new Properties();

    /**
     * 一个线程的线程池用于返回错误
     */
    private ExecutorService pool = Executors.newSingleThreadExecutor();


    /**
     * 缓存哪些方法需要校验
     */
    private static Map<String,Map<Integer,Class[]>> parameterValidMaps = new HashMap<>();

    /**
     * 参数校验
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletResponse response = null;
        Parameter[] parameters = ((MethodSignature) joinPoint.getSignature()).getMethod ().getParameters ();
        Class<?> classTarget=joinPoint.getTarget().getClass();
        String cacheKey =classTarget.getName() + "_" + joinPoint.getSignature().getName();
        // 获取哪些参数需要校验， integer 为需要校验惨的index value的class[] 为校验的group
        Map<Integer,Class[]> vaildObjNamesMap = null;
        if(parameterValidMaps.containsKey(cacheKey))
        {
            vaildObjNamesMap =parameterValidMaps.get(cacheKey);
        }
        else
        {
           vaildObjNamesMap = this.obtainNeedVaildParams (parameters);//获取到需要校验的参数
           parameterValidMaps.put(cacheKey,vaildObjNamesMap);
        }
        List<String> errors = new ArrayList<String> ();
        Object[] params = joinPoint.getArgs();
        int index = 0;

        // 遍历所有的参数，找到需要校验的参数，进行校验并且记录校验结果
        for(Object arg:params){
            if (null == arg){
                continue;
            }
            if(arg instanceof HttpServletResponse){
                response = (HttpServletResponse) arg;
            } else{
                String className = arg.getClass ().getName ();
                if (vaildObjNamesMap.containsKey (index)){//当前实体参数需要校验
                    errors.addAll (this.validate (arg,vaildObjNamesMap.get(index)));
                }
            }
            index++;
        }

        if(errors.size()>0){
            StringBuilder msg = new StringBuilder();
            String defaultMessage = null;
            for(String error :errors){
                defaultMessage = error;
                msg.append(defaultMessage);
                msg.append(",");
            }
            Signature sig = joinPoint.getSignature();
            MethodSignature msig = null;
            msig = (MethodSignature) sig;

            Object target = joinPoint.getTarget();
            Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
            // DeferredResult 为异步api模式 HttpResult 为普通模式
            if(DeferredResult.class == currentMethod.getReturnType())
            {
                HttpResult resultMsg = HttpResult.validateError(new HashMap<String,Object>(), msg.toString());
                DeferredResult<HttpResult> result = new DeferredResult<>();
                pool.submit(new RetrunErrorInfo(result,resultMsg));
                return result;
            } else if(HttpResult.class != currentMethod.getReturnType())
            {
                JsonUtils.outJson(response, HttpResult.validateError(new HashMap<String,Object>(), msg.toString()).asJson());
            }
            else
            {
                return  HttpResult.validateError(currentMethod.getReturnType(), msg.toString());
            }

        }
        return joinPoint.proceed ( );
    }




    /**
     * 实体校验
     * @param obj
     */
    private <T> List<String> validate(T obj,Class[] groups) {
        List<String> errorMsgs = new ArrayList<> ();
        //获得验证器
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        //执行验证
        Set<ConstraintViolation<T>> constraintViolations = groups==null ?
                validator.validate (obj) :
                validator.validate (obj,groups);
        //如果有验证信息，则将第一个取出来包装成异常返回
        constraintViolations.forEach (constraintViolation -> {
            if (constraintViolation != null) {
                errorMsgs.add (constraintViolation.getMessage());
            }
        });
        return errorMsgs;
    }


    /**
     *  获取需要校验的参数的index和group class
     * @param parameters 参数对象
     * @return 需要校验的参数的index和group class
     */
    private Map<Integer,Class[]> obtainNeedVaildParams(Parameter[] parameters) {
        Map<Integer,Class[]> needVaildParams = new HashMap<> ();
        int index = 0;
        for (Parameter p : parameters) {
            Validated annotation = p.getAnnotation (Validated.class);
            Valid validAnnotation = p.getAnnotation (Valid.class);
            if (annotation != null){
                needVaildParams.put (index,annotation.value());
            }else if(validAnnotation !=null)
            {
                needVaildParams.put (index,null);
            }
            index++;
        }
        return needVaildParams;
    }
}

/**
 * 返回错误
 * @Filename: RetrunErrorInfo.java
 * @Description:
 * @Version: 1.0
 * @Author: jackwong
 * @Email: wanglei@sxpartner.com
 * @History:<br>
 * Copyright (c) 2017 All Rights Reserved.
 *
 */
class RetrunErrorInfo extends Thread{



    /**
     *返回对象
     */
    private DeferredResult<HttpResult> result;

    /**
     *错误信息
     */
    HttpResult resultMsg;

    public RetrunErrorInfo(DeferredResult<HttpResult> result, HttpResult resultMsg)
    {
        super();
        this.result = result;
        this.resultMsg = resultMsg;
    }

    @Override
    public void run()
    {
        result.setResult(resultMsg);
    }

}
