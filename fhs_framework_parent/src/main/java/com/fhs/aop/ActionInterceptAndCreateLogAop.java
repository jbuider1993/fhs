package com.fhs.aop;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.*;
import com.fhs.core.api.annotation.AutowareYLM;
import com.fhs.core.log.LogDesc;
import com.fhs.core.result.HttpResult;
import com.fhs.system.api.FeignlogAdminOperatorLogApiService;
import com.fhs.system.bean.LogAdminOperatorLogVo;
import com.fhs.ucenter.api.service.FeignSysMenuApiService;
import com.fhs.ucenter.api.vo.SysMenuVo;
import com.fhs.ucenter.api.vo.SysUserVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 拦截action请求，添加操作日志AOP
 *
 * @Filename: ActionInterceptAndCreateLogAop.java
 * @Description:
 * @Version: 1.0
 * @Author: qixiaobo
 * @Email: qxb@sxpartner.com
 * @History:<br> 陕西小伙伴网络科技有限公司
 * Copyright (c) 2017 All Rights Reserved.
 */
@AutowareYLM
@Aspect
public class ActionInterceptAndCreateLogAop {
    private static final Logger LOGGER = Logger.getLogger(ActionInterceptAndCreateLogAop.class);

    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.fhs.*.action.*.*(..) )")
    public void interceptAndCreateLogAop() {

    }

    /**
     * 日志记录服务
     */
    @AutowareYLM
    private FeignlogAdminOperatorLogApiService feignlogAdminOperatorLogApiService;
    /**
     * 系统菜单服务
     */
    @AutowareYLM
    private FeignSysMenuApiService feignSysMenuApiService;

    /**
     * namesapce:menu map
     */
    private Map<String, SysMenuVo> namesapceMenuMap = new HashMap<>();

    /**
     * 拦截每个Action的增删改查请求并向日志表里添加一条数据
     */
    @Around("interceptAndCreateLogAop()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        String methodName = joinPoint.getSignature().getName();
        Class<?> classTarget = joinPoint.getTarget().getClass();
        Class<?>[] par = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        Method method = classTarget.getMethod(methodName, par);

        // 获取request对象
        HttpServletRequest request = null;
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof HttpServletRequest) {
                request = (HttpServletRequest) arg;
                break;
            }
        }

        if(CheckUtils.isNullOrEmpty(request)) {
            return joinPoint.proceed();
        }

        // 获取请求uri
        String uri = request.getRequestURI();
        // 如果不是以ms开头就不拦截
        if (!uri.contains("/ms/")) {
            // 执行方法
            return joinPoint.proceed();
        }

        // 获取菜单name及nameSpace
        if (namesapceMenuMap.isEmpty()) {
            HttpResult<List<SysMenuVo>> result = feignSysMenuApiService.findIdAndNameAndNamespaceList();
            List<SysMenuVo> sysMenuList = result.getData();
            for (SysMenuVo adminMenu : sysMenuList) {
                namesapceMenuMap.put(adminMenu.getNamespace(), adminMenu);
            }
        }

        // 判断方法上是否包含LogDesc注解
        if (method.isAnnotationPresent(LogDesc.class)) {
            // 获取该注解的内容
            LogDesc annotation = method.getAnnotation(LogDesc.class);
            String operation = annotation.value();
            int type = annotation.type();

            // 获取请求参数
            Map<String, String[]> parameterMap = request.getParameterMap();
            String paramsJson = "";
            if (parameterMap.containsKey("password")) {
                Map<String, String[]> parameterUnLockMap = new HashMap<>();
                for(Map.Entry<String, String[]> entry : parameterMap.entrySet()){
                    if(!entry.getKey().equals("password")) {
                        parameterUnLockMap.put(entry.getKey(), entry.getValue());
                    }
                }
                paramsJson = JsonUtils.map2json(parameterUnLockMap);
            }else {
                paramsJson = JsonUtils.map2json(parameterMap);
            }
            //获取用户ip
            String ip = NetworkUtil.getIpAddress(request);
            // 获取请求用户id
            SysUserVo adminUser = (SysUserVo) request.getSession().getAttribute(Constant.SESSION_USER);
            String userId = adminUser.getUserId();

            // 创建LogAdminOperatorLog 对象,并给各属性赋值
            LogAdminOperatorLogVo log = new LogAdminOperatorLogVo();
            log.setCreateTime(DateUtils.getCurrentDateStr(DateUtils.DATE_FULL_STR_SSS));
            log.setUrl(uri);
            log.setOperatorId(userId);
            log.setReqParam(paramsJson);
            log.setNetworkIp(ip);
            log.setLogType(type);

            String namespace = uri.substring(uri.indexOf("/", uri.indexOf("/") + 1) + 1, uri.lastIndexOf("/"));
            SysMenuVo menu = namesapceMenuMap.get(namespace);
            if (menu != null) {
                log.setMenuId(menu.getMenuId());
                log.setOperatDesc(menu.getMenuName() + ":" + operation);
                feignlogAdminOperatorLogApiService.addLogAdminOperatorLog(log);
            } else {
                LOGGER.warnMsg("namespace:{} 没有找到对应的菜单", namespace);
            }
            // 执行方法
            return joinPoint.proceed();
        }
        return joinPoint.proceed();
    }

}
