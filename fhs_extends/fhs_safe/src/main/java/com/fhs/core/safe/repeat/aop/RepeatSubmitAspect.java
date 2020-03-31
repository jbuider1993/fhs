package com.fhs.core.safe.repeat.aop;

import com.fhs.basics.vo.UcenterMsUserVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.cache.service.RedisCacheService;
import com.fhs.core.exception.HttpException;
import com.fhs.core.exception.ParamException;
import com.fhs.core.safe.repeat.anno.NotRepeat;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.users.AbstractUser;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * 重复提交拦截器
 */
@Aspect
@Component
@Slf4j
public class RepeatSubmitAspect {


    private static final String PRE = "fhs:repeat:token:";

    @Autowired
    private RedisCacheService<Boolean> redisCacheService;


    /**
     * @param pjp
     * @return
     */
    @Around("@annotation(com.fhs.core.safe.repeat.anno.NotRepeat)")
    public Object aroundPringLog(ProceedingJoinPoint pjp) throws Throwable {
        Object rtValue = null;
        String key = null;
        try {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            UcenterMsUserVO user = (UcenterMsUserVO) request.getSession().getAttribute(Constant.SESSION_USER);
            if (user == null) {
                throw new HttpException("请重新登录", HttpException.NO_PERMISSION);
            }
            String methodName = pjp.getSignature().getName();
            key = PRE + pjp.getTarget().getClass() + ":" + methodName + user.getUserId();
            if (ConverterUtils.toBoolean(redisCacheService.get(key))) {
                throw new ParamException("请勿重复提交");
            }
            // 获得被拦截的方法
            Method method = ((MethodSignature) pjp.getSignature()).getMethod();
            NotRepeat notRepeat = method.getAnnotation(NotRepeat.class);
            redisCacheService.put(key, true);
            redisCacheService.expire(key, notRepeat.outTime());
            Object[] args = pjp.getArgs();//得到方法执行所需的参数
            rtValue = pjp.proceed(args);//明确调用业务层方法（切入点方法）
            return rtValue;
        } catch (Exception ex) {
            throw ex;
        } catch (Throwable throwable) {
            throw throwable;
        } finally {
            if (key != null) {
                redisCacheService.remove(key);//清除缓存key
            }
        }
    }
}
