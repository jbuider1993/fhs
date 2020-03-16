package com.fhs.core.jsonfilter.bean;

import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


/**
 * update from https://github.com/Liuyis/jsonfilter
 * by jackwong
 * json过滤器中间对象
 */
@Data
public class JsonFilterObject {
    private Object jsonObject;

    private Map<Class, HashSet<String>> includes = new HashMap<Class, HashSet<String>>();

    private Map<Class, HashSet<String>> excludes = new HashMap<Class, HashSet<String>>();

    public JsonFilterObject() {
    }

    public JsonFilterObject(Object jsonObject, Map<Class, HashSet<String>> includes, Map<Class, HashSet<String>> excludes) {
        this.jsonObject = jsonObject;
        this.includes = includes;
        this.excludes = excludes;
    }


}
