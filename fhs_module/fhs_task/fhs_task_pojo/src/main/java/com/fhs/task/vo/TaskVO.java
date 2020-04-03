package com.fhs.task.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 定时任务
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskVO {
    /**
     * 任务名称
     */
    @NotEmpty(message = "任务名称不可为空")
    private String jobName;
    /**
     * 分组
     */
    @NotEmpty(message = "任务分组不可为空")
    private String jobGroup;
    /**
     * 描述
     */
    @NotEmpty(message = "任务描述不可为空")
    private String description;
    /**
     * 类名字
     */
    @NotEmpty(message = "任务类不可为空")
    private String jobClassName;
    /**
     * 执行时间表达式
     */
    @NotEmpty(message = "时间表达式不可为空")
    private String cronExpression;
    private String triggerName;
    private String triggerState;
    private String oldJobName;
    private String oldJobGroup;
}
