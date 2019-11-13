package com.fhs.workflow.service.impl;

import java.util.List;
import java.util.Map;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.workflow.bean.FlowTask;
import com.fhs.workflow.dao.WorkFlowTaskDAO;
import com.fhs.workflow.service.WorkFlowTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 任务service
 * 主要用来查询任务使用，完成任务或者签收以及其他操作使用引擎自带的taskservice操作
 * @author wanglei
 *
 */
@Service("WorkFlowTaskServiceImpl")
public class WorkFlowTaskServiceImpl  implements WorkFlowTaskService {

    @Autowired
    private WorkFlowTaskDAO workFlowTaskDAO;
    
    @Override
    public List<FlowTask> findNeedClaimTask(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedClaimTask(paramMap);
    }

    @Override
    public int findNeedClaimTaskCount(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedClaimTaskCount(paramMap);
    }

    @Override
    public List<FlowTask> findNeedComplateTask(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedComplateTask(paramMap);
    }

    @Override
    public int findNeedComplateTaskCount(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedComplateTaskCount(paramMap);
    }


}
