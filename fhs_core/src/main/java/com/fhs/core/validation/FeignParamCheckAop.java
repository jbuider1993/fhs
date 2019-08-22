package com.fhs.core.validation;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 *
 * spring cloud接口调用参数校验aop
 * @author qh
 */
@Aspect
public class FeignParamCheckAop extends ParamCheckAop{



    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.*.*.api..*.*(..) )")
    public void checkParam(){

    }


    @Before("checkParam()")
    public void doBefore(JoinPoint joinPoint) {
    }

    /**
     * 参数校验
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("checkParam()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        return super.doAround(joinPoint);
    }
    /**
     * 在切入点return内容之后切入内容（可以用来对处理返回值做一些加工处理）
     *
     * @param joinPoint
     */
    @AfterReturning("checkParam()")
    public void doAfterReturning(JoinPoint joinPoint) {
    }
}
