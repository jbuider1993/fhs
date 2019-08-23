package com.fhs.core.jsonfilter.bean;

import lombok.Data;

/**
 * vo转换器
 */
@Data
public class VoConverterObject {

    //转换配置
    private String[]  settings;

    //不需要转换但是需要包含的字段
    private String[]  include;

    //实际对象
    private Object JsonObject;
}
