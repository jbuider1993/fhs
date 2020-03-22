package com.fhs.core.valid.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 其他的控制器校验aop
 */
@Aspect
public class OrdinaryActionParamCheckAop extends ParamCheckAop {
    /**
     * 定义切入点
     * com.xhb.tour.controller
     */
    @Pointcut("execution(* com.*.*.action..*.*(..) )")
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
    @Override
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
