package com.fhs.core.responsebody.aop;

import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ReflectUtils;
import com.fhs.core.base.bean.BaseObject;
import com.fhs.core.responsebody.annotation.JsonFilter;
import com.fhs.core.responsebody.annotation.ObjFilter;
import com.fhs.core.result.HttpResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 主键生成策略
 *
 * @author jianbo.qin
 */
@Aspect
@Component
public class JsonReturnAspect {

    private static Logger LOG = LoggerFactory.getLogger(JsonReturnAspect.class);


    @AfterReturning(pointcut = "@annotation(com.fhs.core.responsebody.annotation.JsonFilter)",
            returning = "returnValue")
    public void log(JoinPoint joinPoint, Object returnValue) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        JsonFilter jsonFilter = method.getAnnotation(JsonFilter.class);
        handleObject(returnValue, jsonFilter);
    }

    /**
     * 处理httpresult
     */
    private void HttpResultHandel(HttpResult result, JsonFilter jsonFilter) {
        Object obj = result.getData();
        handleObject(obj, jsonFilter);
    }

    /**
     * 处理object
     *
     * @param obj
     */
    private void handleObject(Object obj, JsonFilter jsonFilter) {
        if (obj instanceof HttpResult) {
            HttpResultHandel((HttpResult) obj, jsonFilter);
        } else if (obj instanceof BaseObject) {
            handleBaseObject( obj,  jsonFilter);
        } else if (obj instanceof Collection) {
            handleCollection((Collection) obj, jsonFilter);
        } else if (obj.getClass().isArray()) {
            handleArray((Object[]) obj, jsonFilter);
        }
    }

    /**
     * 处理集合
     *
     * @param collection
     */
    private void handleCollection(Collection collection, JsonFilter jsonFilter) {
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            handleObject(it.next(), jsonFilter);
        }
    }

    /**
     * 处理数组
     *
     * @param objs
     */
    private void handleArray(Object[] objs, JsonFilter jsonFilter) {
        for (Object obj : objs) {
            handleObject(obj, jsonFilter);
        }
    }

    /**
     * 处理baseObject
     *
     * @param obj
     * @param jsonFilter
     */
    private void handleBaseObject(Object obj, JsonFilter jsonFilter) {
        ObjFilter[] objFilters = jsonFilter.value();
        for (ObjFilter filter : objFilters) {
            Object target = CheckUtils.isNullOrEmpty(filter.expression()) ? obj : findObjectByExpression(obj, filter.expression());
            if (target != null) {
                String[] excludeFields = filter.excludeField();
                List<String> excludeFieldList = new ArrayList<>();
                excludeFieldList.addAll(Arrays.asList(excludeFields));
                if (filter.excludeField() == null || filter.excludeField().length==0) {
                    final String[] includeFields = filter.includeField();
                    final Set<String> includeFieldSet = new HashSet<>();
                    includeFieldSet.addAll(Arrays.asList(includeFields));
                    List<Field> fields = ReflectUtils.getAllField(target);
                    fields.forEach(field -> {
                        if(!includeFieldSet.contains(field.getName()))
                        {
                            excludeFieldList.add(field.getName());
                        }
                    });
                }
                for (String excludeField : excludeFieldList) {
                    ReflectUtils.setValue(target,excludeField,null);
                }
            }
        }
    }

    /**
     * 根据expression找到对应的object
     *
     * @param obj
     * @param expression
     */
    private Object findObjectByExpression(Object obj, String expression) {
        if (obj == null) {
            return null;
        }
        if (CheckUtils.isNotEmpty(expression)) {
            return null;
        }
        String fieldName = expression.split(".")[0];
        Object object = ReflectUtils.getValue(obj, fieldName);
        if (fieldName.equals(expression)) {
            return object;
        }
        expression = expression.replace(fieldName + ".", "");
        return findObjectByExpression(object, expression);
    }
}