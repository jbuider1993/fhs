package com.fhs.flow.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.flow.dox.FlowTaskHistoryDO;
import com.fhs.flow.vo.FlowTaskHistoryVO;
import com.fhs.flow.vo.TaskHistoryVO;
import java.util.List;
import java.util.Map;

/**
 * 流程任务日志(FlowTaskHistory)表服务接口
 *
 * @author jackwong
 * @since 2019-11-12 14:40:34
 */
public interface FlowTaskHistoryService extends BaseService<FlowTaskHistoryVO, FlowTaskHistoryDO>{

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
     * 撤回审批
     */
    int RESULT_WITHDRAW = 4;

    /**
     * 根据当前需要记录节点的usertaskkey和instanceId来构建一个FlowTaskHistory
     * 处理好code字段，和排序字段
     * @param definitionKey usertaskId
     * @param instanceId 流程实例id
     * @return 构建好的对象
     */
    FlowTaskHistoryVO buildFlowTaskHistory(String definitionKey, String instanceId);

    /**
     * 查询已办纪录
     * @param paramMap
     * @return
     */
    List<TaskHistoryVO> findTaskHistoryList(Map<String, Object> paramMap);

    /**
     * 查询已办纪录总数
     * @param paramMap
     * @return
     */
    int findTaskHistoryCount(Map<String, Object> paramMap);

    /**
     * 审批纪录
     */
    List<TaskHistoryVO> findApprovalRecord(String instanceId);


}