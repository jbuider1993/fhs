package com.fhs.workflow.service;

import java.util.List;
import java.util.Map;

import com.fhs.core.base.service.BaseService;
import com.fhs.workflow.bean.FlowTask;

/**
 * 任务service 主要用来查询任务使用，完成任务或者签收以及其他操作使用引擎自带的taskservice操作
 * 
 * @author wanglei
 *
 */
public interface WorkFlowTaskService {

    /**
     * 查询代签收任务
     * 
     * @param paramMap
     *            参数map
     * @return 代签收map集合
     */
    List<FlowTask> findNeedClaimTask(Map<String, Object> paramMap);

    /**
     * 查询代签收任务总数
     * 
     * @param paramMap
     *            参数map
     * @return 代签收总数
     */
    int findNeedClaimTaskCount(Map<String, Object> paramMap);

    /**
     * 查询待办任务
     * @param paramMap 参数map
     * @return 代签收map集合
     */
    List<FlowTask> findNeedComplateTask(Map<String, Object> paramMap);


    /**
     * 查询待办任务总数
     * @param paramMap 参数map
     * @return 代签收总数
     */
    int findNeedComplateTaskCount(Map<String, Object> paramMap);
}
