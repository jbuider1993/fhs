package com.fhs.core.base.controller;

import com.alibaba.fastjson.JSONObject;
import com.fhs.common.spring.SpringContextUtil;
import com.fhs.common.utils.JsonUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.exception.ParamException;
import com.fhs.core.valid.checker.ParamChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 所有的api请求都通过此接口走
 */
@Controller
@Slf4j
public class ApiController {


    @Value("${fhs.api.password:fhs-framework}")
    private String apiToken;

    /**
     * 执行RPC方法
     *
     * @param serviceClass 方法类
     * @param methodName   方法名
     * @param request      req
     * @param response     res
     */
    @RequestMapping("/api/{serviceClass}/{methodName}")
    public void doExec(@PathVariable() String serviceClass,@PathVariable() String methodName, HttpServletRequest request, HttpServletResponse response) {
        ParamChecker.isNotNullOrEmpty(serviceClass, "serviceClass 不可为空");
        ParamChecker.isNotNullOrEmpty(methodName, "methodName 不可为空");
        if(!this.apiToken.equals(request.getHeader("apiToken"))){
            throw new ParamException("token不正确,非法调用");
        }
        try {
            Class clazz = Class.forName(serviceClass);
            if (!clazz.isAnnotationPresent(FeignClient.class)) {
                throw new ParamException("调用方法非Feign接口");
            }
            Method method = ReflectUtils.getMethodd(clazz, methodName);
            if (method == null) {
                throw new ParamException("方法不存在");
            }

            Object[] params = new Object[method.getParameterTypes().length];
            Annotation[][] annotations = method.getParameterAnnotations();
            int index = 0;
            outside:
            for (Annotation[] oneParamAnnotations : annotations) {
                for (Annotation annotation : oneParamAnnotations) {
                    if (annotation instanceof RequestBody) {
                        String jsonString = readAsChars(request);
                        params = new Object[]{JSONObject.parseObject(jsonString, method.getParameters()[0].getType())};
                        break outside;
                    } else if (annotation instanceof RequestParam) {
                        RequestParam tempParam = (RequestParam)annotation;
                        params[index] = request.getParameter(tempParam.defaultValue());
                    }
                }
                index++;
            }
            Object service = SpringContextUtil.getBeanByClassForApi(clazz);
            try {
                Object result = method.invoke(service, params);
                JsonUtils.outJson(response, JsonUtils.bean2json(result));
            } catch (IllegalAccessException e) {
                log.error("", e);
            } catch (InvocationTargetException e) {
                log.error("", e);
            }
        } catch (ClassNotFoundException e) {
            throw new ParamException("serviceClass 名称错误");
        }
    }

    /**
     * 从request中读取body
     *
     * @param request request
     * @return body json
     */
    public String readAsChars(HttpServletRequest request) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
            br.close();
        } catch (IOException e) {
            log.error("", e);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }
        return sb.toString();
    }
}
