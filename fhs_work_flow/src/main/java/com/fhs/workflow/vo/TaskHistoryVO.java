package com.fhs.workflow.vo;

import com.fhs.common.constant.Constant;
import com.fhs.core.base.bean.SuperBean;
import com.fhs.core.trans.Trans;
import com.fhs.core.trans.TransTypes;
import lombok.Data;

/**
 * 已办纪录
 *
 * @author yutao
 * @date 2019 -11-15 09:47:12
 */
@Data
@TransTypes(types = {Constant.USER_INFO,"wordbook"})
public class TaskHistoryVO extends SuperBean<TaskHistoryVO> {

    private String taskId;

    /**
     * 流程标题
     */
    private String processTitle;

    /**
     * 流程名称
     */
    private String processName;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 流程状态
     */
    @Trans(type = "wordbook", key = "workFlow_process_status")
    private String processStatus;

    /**
     * 发起人
     */
    @Trans(type = Constant.USER_INFO, key = Constant.USER_NAME)
    private String createUser;

    /**
     * 办理时间
     */
    private String taskFinishTime;

    /**
     * 处理用时
     */
    private String useTime;

    /**
     * 处理结果
     */
    @Trans(type = "wordbook", key = "workFlow_use_status")
    private String useStatus;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 备注
     */
    private String remark;
}
