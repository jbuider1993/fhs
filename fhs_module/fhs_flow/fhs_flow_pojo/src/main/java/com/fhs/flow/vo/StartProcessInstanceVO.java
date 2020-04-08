package com.fhs.flow.vo;

import lombok.Data;

import java.util.Map;

/**
 * 启动流程实例
 */
@Data
public class StartProcessInstanceVO {
    /**
     * 流程key
     */
    private String processDefinitionKey;
    /**
     * 业务key(业务表id)
     */
    private String businessKey;
    /**
     * 流程变量
     */
    private Map<String, Object> variables;
    /**
     * 表单参数
     */
    private  Map<String, Object>  extFormParam;
    /**
     * 用户id
     */
    private String userId;
}
