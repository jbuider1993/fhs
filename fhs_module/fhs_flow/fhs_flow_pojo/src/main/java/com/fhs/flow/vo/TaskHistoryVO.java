package com.fhs.flow.vo;

import com.fhs.common.constant.Constant;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.anno.TransTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 已办纪录
 *
 * @author yutao
 * @date 2019 -11-15 09:47:12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TransTypes(types = {Constant.USER_INFO,"wordbook"})
public class TaskHistoryVO {

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
