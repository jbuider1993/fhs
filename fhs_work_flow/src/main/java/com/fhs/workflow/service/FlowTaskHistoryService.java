package com.fhs.workflow.service;

import com.fhs.workflow.bean.FlowTaskHistory;
import java.util.List;
import com.fhs.core.base.service.BaseService;
/**
 * 流程任务日志(FlowTaskHistory)表服务接口
 *
 * @author jackwong
 * @since 2019-11-12 14:40:34
 */
public interface FlowTaskHistoryService extends BaseService<FlowTaskHistory>{

    /**
     * 未完成
     */
    int STATUS_TODO = 0;

    /**
     * 已完成
     */
    int STATUS_FINISH =1;


    /**
     * 提交申请
     */
    int RESULT_SUBMIT = 6;

    /**
     * 同意
     */
    int RESULT_PASS = 0;

    /**
     * 驳回
     */
    int RESULT_BACK = 1;

    /**
     * 人工终止
     */
    int RESULT_END = 2;

    /**
     * 撤销申请
     */
    int RESULT_REVOKE = 3;

    /**
     * 撤销回审批
     */
    int RESULT_WITHDRAW = 4;

}