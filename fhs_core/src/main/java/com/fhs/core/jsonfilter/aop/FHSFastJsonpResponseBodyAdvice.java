package com.fhs.core.jsonfilter.aop;

import com.fhs.core.jsonfilter.annotation.JsonFilter;
import com.fhs.core.jsonfilter.annotation.ObjFilter;
import com.fhs.core.jsonfilter.bean.JsonFilterObject;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;
import java.util.HashSet;

/**
 * update from https://github.com/Liuyis/jsonfilter
 * by jackwong
 * json转换器前置解析注解内容
 */
@Order(1)
@ControllerAdvice
@Component
public class FHSFastJsonpResponseBodyAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        JsonFilterObject jsonFilterObject = new JsonFilterObject();
        if (o == null) {
            return null;
        }
        if (!methodParameter.getMethod().isAnnotationPresent(JsonFilter.class)) {
            return o;
        }
        ObjFilter[] objFilters = methodParameter.getMethod().getAnnotation(JsonFilter.class).value();
        for (ObjFilter filter : objFilters) {
            jsonFilterObject.getExcludes().put(filter.clazz(),new HashSet<>(Arrays.asList(filter.excludeField())));
            jsonFilterObject.getIncludes().put(filter.clazz(),new HashSet<>(Arrays.asList(filter.includeField())));
        }
        jsonFilterObject.setJsonObject(o);
        return jsonFilterObject;

    }


}

