package com.fhs.flow.dox;


import com.fhs.common.constant.Constant;

import com.fhs.core.base.dox.BaseDO;
import com.fhs.core.trans.anno.Trans;
import lombok.*;

/**
 * 任务-主要用于待办和待签收
 *
 * @author Jackwong
 * @date 2019 -11-11 16:37:44
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FlowTaskDO extends BaseDO<FlowTaskDO> {

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 负责人--暂时不清楚
     */
    private String taskOwner;

    /**
     * 任务处理人
     */
    private String assignee;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 任务创建时间
     */
    private String taskCreateTime;

    /**
     * 实例创建时间
     */
    private String instanceCreateTime;


    private String dueDate;

    private String category;

    private String suspensionSate;

    private String formKey;

    private String fhsInstanceId;

    /**
     * 流程引擎实例id
     */
    private String activitiInstanceId;

    /**
     * 标题
     */
    private String title;

    /**
     * 实例创建人
     */
    private String createUser;


}
