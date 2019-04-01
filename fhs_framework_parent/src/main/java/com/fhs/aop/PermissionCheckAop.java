//package com.fhs.aop;
//
//import com.fhs.common.utils.CheckUtils;
//import com.fhs.common.utils.JsonUtils;
//import com.fhs.common.utils.Logger;
//import com.fhs.core.exception.CheckException;
//import com.fhs.core.permission.PermissionCode;
//import com.fhs.core.result.HttpResult;
//import com.fhs.core.result.PubResult;
//import com.fhs.ucenter.api.vo.FrontUserVO;
//import com.fhs.ucenter.bean.FrontUser;
//import net.minidev.json.JSONUtil;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Pointcut;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//import java.util.List;
//import java.util.Set;
//
///**
// * @Description:接口访问权限AOP
// * @Version: 1.0
// * @Author: qiuhang
// */
//@Aspect
//public class PermissionCheckAop
//{
//    private static final Logger LOG = Logger.getLogger(PermissionCheckAop.class);
//
//
//    /**
//     * 定义切入点
//     */
//    @Pointcut("execution(* com.fhs.*.action.*.*(..) )")
//    public void checkPermission(){
//
//    }
//
//    @Around("checkPermission()")
//    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable
//    {
//        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = sra.getRequest();
//        HttpServletResponse response = sra.getResponse ();
//
//        String methodName = joinPoint.getSignature().getName();
//        Class<?> classTarget = joinPoint.getTarget().getClass();
//        Class<?>[] par = ((MethodSignature)joinPoint.getSignature()).getParameterTypes();
//        Method method = classTarget.getMethod(methodName, par);
//        Class<?> returnType = method.getReturnType ( );
//        // 判断方法上是否包含PermissionCode注解
//        if (method.isAnnotationPresent(PermissionCode.class))
//        {
//            // 获取该注解的内容
//            PermissionCode annotation = method.getAnnotation(PermissionCode.class);
//            String currentRole = annotation.value();
//
//            Object obj_user = request.getSession ().getAttribute ("frontUser");
//            Object obj_roles = request.getSession ().getAttribute ("frontUserRoles");
//
//            if (!CheckUtils.isNullOrEmpty(obj_user) && !CheckUtils.isNullOrEmpty(obj_roles)){
//                FrontUserVO frontUser = (FrontUserVO) obj_user;
//                List<String> frontUserRoles = (List<String>) obj_roles;
//
//                if (!frontUserRoles.contains(currentRole))
//                {
//                    LOG.infoMsg("当前用户id为{}无此接口{}权限!", frontUser.getUserId (), methodName);
//
//                    if (Void.class == returnType){
//                        throw new CheckException(PubResult.NO_PERMISSION.asResult());
//                    }else{
//                        throw new CheckException(PubResult.NO_PERMISSION.asResult(returnType));
//                    }
//                }
//            }else{
//                throw new CheckException(PubResult.NO_PERMISSION.asResult(returnType));
//            }
//        }
//        // 执行方法
//        return joinPoint.proceed();
//
//    }
//
//}
