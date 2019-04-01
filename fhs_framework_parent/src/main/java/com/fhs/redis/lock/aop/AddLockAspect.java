package com.fhs.lock.aop;

import com.fhs.common.spring.SpelUtil;
import com.fhs.common.utils.Logger;
import com.fhs.core.exception.BusinessException;
import com.fhs.lock.annotations.AddLock;
import com.fhs.redis.service.RedisLockService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * redis分布式锁实现
 * @ProjectName: framework_v2_idea2
 * @Package: com.fhs.lock.aop
 * @ClassName: AddLockAspect
 * @Author: JackWang
 * @CreateDate: 2019/1/8 0008 14:19
 * @UpdateUser: JackWang
 * @UpdateDate: 2019/1/8 0008 14:19
 * @Version: 1.0
 */
@Aspect
@Component
public class AddLockAspect {

    /**
     * 日志记录
     */
    private static final Logger LOG = Logger.getLogger(AddLockAspect.class);

    @Resource
    private RedisLockService redisLockService;

    @Pointcut("@annotation(com.fhs.lock.annotations.AddLock)")
    public void addLockAnnotationPointcut() {

    }

    @Around(value = "addLockAnnotationPointcut()")
    public Object addKeyMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        //定义返回值
        Object proceed = null;
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        Object target = joinPoint.getTarget();
        Object[] arguments = joinPoint.getArgs();
        AddLock annotation = AnnotationUtils.findAnnotation(targetMethod, AddLock.class);
        String spel=null;
        if(annotation != null){
            spel = annotation.key();
        }
        //前置方法 开始
        String redisKey =  "fhslock:" + SpelUtil.parse(target,spel, targetMethod, arguments);
        LOG.infoMsg("添加redisKey={}",redisKey);
        boolean isLock = false;
        long excuteTime = 0;
        try {
            //true代表成功了，false代表加锁失败
            isLock = redisLockService.addRedisLock(redisKey,annotation.maxWait(),annotation.timeout());
            // 目标方法执行
            if(isLock){
                excuteTime = new Date().getTime();
                proceed = joinPoint.proceed();
            }
        } catch (Exception exception) {
            //如果我自己加锁成功，出了异常则将锁释放掉
            if(isLock) {
                redisLockService.delRedisLock(redisKey);
            }
            throw exception;
        } finally {
            //加锁失败抛出异常
            if (!isLock) {
                throw new BusinessException(redisKey + "add LockTimeout,key:" + redisKey);
            }
            else
            {
                //如果当前时间 减去 service方法开始执行时间大于timeout的话，可能redis已经自动释放锁了，所以这边不在主动释放锁
                if((new Date().getTime() - excuteTime) < annotation.timeout()*1000)
                {
                    redisLockService.delRedisLock(redisKey);
                }
                return proceed;
            }
        }
    }


}

