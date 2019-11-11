package com.fhs.workflow.service.impl;

import java.util.List;
import java.util.Map;

import com.fhs.core.base.service.impl.BaseServiceImpl;
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
public class WorkFlowTaskServiceImpl extends BaseServiceImpl<Map<String,Object>> implements WorkFlowTaskService {

    @Autowired
    private WorkFlowTaskDAO workFlowTaskDAO;
    
    @Override
    public List<Map<String, Object>> findNeedClaimTask(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedClaimTask(paramMap);
    }

    @Override
    public int findNeedClaimTaskCount(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedClaimTaskCount(paramMap);
    }

  

}