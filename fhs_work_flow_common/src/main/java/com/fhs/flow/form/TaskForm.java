package com.fhs.flow.form;

import lombok.Data;
import org.activiti.engine.task.IdentityLink;

import java.util.Date;
import java.util.Set;

/**
 * 节点form
 *
 * @author yutao
 * @date 2019 -11-13 11:50:53
 */
@Data
public class TaskForm {

    /**
     * 数据库中的taskId主键
     */
    private String id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 任务的描述信息,可修改
     */
    private String description;

    /**
     * 任务处理的优先级范围是0-100，可修改
     */
    private Integer priority;

    /**
     * 流程实例id
     */
    private String processInstanceId;

    /**
     * 执行id
     */
    private String executionId;

    /**
     * 流程定义id
     */
    private String processDefinitionId;

    private Date createTime;

    /**
     * 任务定义键
     */
    private String taskDefinitionKey;

    /**
     * 暂停的
     */
    private Boolean isSuspended;

    /**
     *
     */
    private String tenantId;

    private String formKey;

    /**
     * 事件名称
     */
    private String eventName;

    /**
     * owner字段就是用于受理人委托别人操作的时候运用的字段
     */
    private String owner;

    /**
     * 受托人,代理人，负责人
     */
    private String assignee;

    /**
     * 到期日
     */
    private Date dueDate;

    /**
     * 类别
     */
    private String category;

    /**
     * 侯选人
     */
    private Set<IdentityLink> candidates;
}
