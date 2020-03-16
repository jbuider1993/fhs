package com.fhs.pagex.fi;

import com.alibaba.fastjson.JSONArray;

import java.util.Map;

/**
 * 处理字段数据 的pagex数据处理器
 * 勇于数据格式化
 */
@FunctionalInterface
public interface ListDataExtendsHandle {

    /**
     * 处理数据的方法
     * @param fieldSett  列配置 camelName可以获取到数据库字段的驼峰写法，以此当key可以在rows的每row中获取数据
     * @param rows 数据
     */
    void handleData(Map<String, Object> fieldSett, JSONArray rows);

}
