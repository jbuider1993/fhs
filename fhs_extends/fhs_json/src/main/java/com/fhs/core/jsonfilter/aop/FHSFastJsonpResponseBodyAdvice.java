package com.fhs.core.jsonfilter.aop;

import com.fhs.core.jsonfilter.anno.JsonFilter;
import com.fhs.core.jsonfilter.anno.ObjFilter;
import com.fhs.core.jsonfilter.anno.VoFormatter;
import com.fhs.core.jsonfilter.bean.JsonFilterObject;
import com.fhs.core.jsonfilter.bean.VoConverterObject;
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
        if (o == null) {
            return null;
        }
        if (methodParameter.getMethod().isAnnotationPresent(JsonFilter.class)) {
            JsonFilterObject jsonFilterObject = new JsonFilterObject();
            ObjFilter[] objFilters = methodParameter.getMethod().getAnnotation(JsonFilter.class).value();
            for (ObjFilter filter : objFilters) {
                jsonFilterObject.getExcludes().put(filter.clazz(),new HashSet<>(Arrays.asList(filter.excludeField())));
                jsonFilterObject.getIncludes().put(filter.clazz(),new HashSet<>(Arrays.asList(filter.includeField())));
            }
            jsonFilterObject.setJsonObject(o);
            return jsonFilterObject;
        }
        if (methodParameter.getMethod().isAnnotationPresent(VoFormatter.class)) {
            VoFormatter voFormatter =  methodParameter.getMethod().getAnnotation(VoFormatter.class);
            VoConverterObject voConverterObject = new VoConverterObject();
            voConverterObject.setSettings(voFormatter.value());
            voConverterObject.setInclude(voFormatter.include());
            voConverterObject.setJsonObject(o);
            return voConverterObject;
        }
        return o;


    }


}

