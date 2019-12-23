package com.fhs.flow.form;

import lombok.Data;
import org.activiti.engine.EngineServices;

/**
 * 连线Form
 *
 * @author yutao
 * @date 2019 -11-13 11:50:30
 */
@Data
public class ExecutionForm {

    private String id;

    /**
     * 流程实例id
     */
    private String processInstanceId;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * 业务id已经废弃
     */
    private String businessKey;

    /**
     * 业务id，通过业务id可以修改我们业务单的状态
     */
    private String processBusinessKey;

    /**
     * 流程定义id
     */
    private String processDefinitionId;

    /**
     * 获取父id，并发的时候有用
     */
    private String parentId;

    /**
     * 获取当前的.Activityid
     */
    private String currentActivityId;

    /**
     * 获取当前的.Activity name
     */
    private String currentActivityName;

    /**
     * 获取TenantId 当有多个TenantId 有用
     */
    private String TenantId;

    /**
     * 超级执行ID
     */
    private String superExecutionId;



}
