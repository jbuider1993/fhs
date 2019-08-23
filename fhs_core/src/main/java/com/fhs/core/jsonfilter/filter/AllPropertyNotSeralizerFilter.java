package com.fhs.core.jsonfilter.filter;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.fhs.core.jsonfilter.bean.VoConverterObject;
import com.fhs.core.result.HttpResult;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 除了配置的字段外的字段都不参与序列化
 * by wanglei
 */
public class AllPropertyNotSeralizerFilter  extends SimplePropertyPreFilter {

    //转换配置
    private Set<String> includeField;

    public AllPropertyNotSeralizerFilter(VoConverterObject voConverterObject)
    {
        if(voConverterObject.getInclude()==null)
        {
            includeField = new HashSet<>();
            return;
        }
        includeField = new HashSet<>(Arrays.asList(voConverterObject.getInclude()));
    }

    @Override
    public boolean apply(JSONSerializer serializer, Object source, String name) {
        if(source instanceof HttpResult)
        {
            return true;
        }
        return includeField.contains(name);
    }
}
