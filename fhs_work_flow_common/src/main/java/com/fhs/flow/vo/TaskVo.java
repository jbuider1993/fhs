package com.fhs.flow.vo;

import lombok.Data;
import org.activiti.engine.delegate.DelegateTask;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 节点可赋值的属性
 *
 * @author yutao
 * @date 2019 -11-13 15:12:17
 */
@Data
public class TaskVo{

    private String name;
    private String description;
    private Integer priority;
    private String formKey;
    private String owner;
    private String assignee;
    private Date dueDate;
    private String category;

    /** 添加候选人 */
    private Collection<String> candidateUsers;

    /** 添加候选组 */
    private String groupId;

    /** 流程变量 */
    private Map<String, Object> variableMap;
}
