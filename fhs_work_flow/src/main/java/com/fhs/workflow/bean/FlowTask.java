package com.fhs.workflow.bean;


import com.fhs.common.constant.Constant;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransTypes;
import lombok.Data;

/**
 * 任务-主要用于待办和待签收
 *
 * @author Jackwong
 * @date 2019 -11-11 16:37:44
 */
@Data
@TransTypes(types = {Constant.USER_INFO})
public class FlowTask extends SuperBean<FlowTask> {

    //任务id
    private String taskId;

    //任务名称
    private String taskName;

    // 负责人--暂时不清楚
    private String taskOwner;

    //任务处理人
    @Trans(type = Constant.USER_INFO, key = Constant.USER_NAME)
    private String assignee;

    //优先级
    private Integer priority;

    //任务创建时间
    private String taskCreateTime;

    // 实例创建时间
    private String instanceCreateTime;


    private String dueDate;

    private String category;

    private String suspensionSate;

    private String formKey;

    private String fhsInstanceId;

    //流程引擎实例id
    private String activitiInstanceId;

    // 标题
    private String title;

    //实例创建人
    @Trans(type = Constant.USER_INFO, key = Constant.USER_NAME)
    private String createUser;

    public FlowTask() {
    }

    public FlowTask(String taskId, String taskName, String taskOwner, String assignee, Integer priority, String taskCreateTime, String instanceCreateTime, String dueDate, String category, String suspensionSate, String formKey, String fhsInstanceId, String activitiInstanceId, String title, String createUser) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskOwner = taskOwner;
        this.assignee = assignee;
        this.priority = priority;
        this.taskCreateTime = taskCreateTime;
        this.instanceCreateTime = instanceCreateTime;
        this.dueDate = dueDate;
        this.category = category;
        this.suspensionSate = suspensionSate;
        this.formKey = formKey;
        this.fhsInstanceId = fhsInstanceId;
        this.activitiInstanceId = activitiInstanceId;
        this.title = title;
        this.createUser = createUser;
    }
}
