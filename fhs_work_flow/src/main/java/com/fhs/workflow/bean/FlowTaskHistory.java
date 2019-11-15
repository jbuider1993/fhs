package com.fhs.workflow.bean;

import java.io.Serializable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransTypes;
import com.mybatis.jpa.annotation.*;
import com.fhs.core.group.*;
import com.fhs.common.constant.Constant;
import com.fhs.core.base.bean.BaseDO;

import javax.validation.constraints.*;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.fhs.core.base.bean.BaseDO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 流程任务日志(FlowTaskHistory)实体类
 *
 * @author jackwong
 * @since 2019-11-12 14:40:34
 */

@Data
@Builder
@TableName("t_flow_task_history")
@TransTypes(types = {Constant.USER_NAME})
public class FlowTaskHistory extends BaseDO<FlowTaskHistory> {
    private static final long serialVersionUID = -88447633213612521L;
    @TableId(value = "id", type = IdType.UUID)
    private String id;

    /**
     * activiti 实例id
     */
    @Length(message = "${column.comment}字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("instance_id")
    private String instanceId;

    /**
     * 完成时间
     */
    @Length(message = "完成时间字段的长度最大为20", groups = {Add.class, Update.class}, max = 20)
    @TableField("task_finish_time")
    private String taskFinishTime;

    /**
     * 任务id
     */
    @Length(message = "任务id字段的长度最大为32", groups = {Add.class, Update.class}, max = 32)
    @TableField("task_id")
    private String taskId;

    /**
     * 候选用户
     */
    @Length(message = "候选用户字段的长度最大为512", groups = {Add.class, Update.class}, max = 512)
    @TableField("candidate_user_id")
    private String candidateUserId;

    /**
     * 候选角色
     */
    @Length(message = "候选角色字段的长度最大为512", groups = {Add.class, Update.class}, max = 512)
    @TableField("candidate_rule_id")
    private String candidateRuleId;

    /**
     * 0 未完成 1 已完成
     */
    @NotNull(message = "0 未完成 1 已完成字段不可为null", groups = {Update.class, Delete.class})
    @TableField("status")
    @Trans(type = Constant.WORD_BOOK,key="task_history_status")
    private Integer status;

    /**
     *  任务标题
     */
    @TableField("title")
    private String title;

    /**
     *  任务父taskid
     */
    @TableField("parent_task_id")
    private String parentTaskId;

    /**
     * 使用时间
     */
    @TableField("use_time")
    private Integer useTime;

    /**
     * 完成用户id
     */
    @TableField("assignee_user_id")
    @Trans(type = Constant.USER_INFO, key = Constant.USER_NAME)
    private String assigneeUserId;

    /**
     * 结果
     */
    private Integer result;

    /**
     * 备注
     */
    private String remark;

    /**
     * 节点编码
     */
    private String code;

    /**
     * 节点编码
     */
    private Integer orderNum;

    /**
     * 节点编码
     */
    private String definitionKey;



    public FlowTaskHistory() {
    }

    public FlowTaskHistory(String id, String instanceId, String taskFinishTime, String taskId, String candidateUserId, String candidateRuleId, Integer status, String title, String parentTaskId, Integer useTime, String assigneeUserId, Integer result, String remark, String code, Integer orderNum, String definitionKey) {
        this.id = id;
        this.instanceId = instanceId;
        this.taskFinishTime = taskFinishTime;
        this.taskId = taskId;
        this.candidateUserId = candidateUserId;
        this.candidateRuleId = candidateRuleId;
        this.status = status;
        this.title = title;
        this.parentTaskId = parentTaskId;
        this.useTime = useTime;
        this.assigneeUserId = assigneeUserId;
        this.result = result;
        this.remark = remark;
        this.code = code;
        this.orderNum = orderNum;
        this.definitionKey = definitionKey;
    }
}