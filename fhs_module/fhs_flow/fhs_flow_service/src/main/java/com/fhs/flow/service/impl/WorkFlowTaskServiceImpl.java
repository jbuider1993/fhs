package com.fhs.flow.service.impl;

import com.fhs.flow.dox.FlowTaskDO;
import com.fhs.flow.mapper.WorkFlowTaskMapper;
import com.fhs.flow.service.WorkFlowTaskService;
import com.fhs.flow.vo.FlowTaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 任务service
 * 主要用来查询任务使用，完成任务或者签收以及其他操作使用引擎自带的taskservice操作
 * @author wanglei
 *
 */
@Service("WorkFlowTaskServiceImpl")
public class WorkFlowTaskServiceImpl  implements WorkFlowTaskService {

    @Autowired
    private WorkFlowTaskMapper workFlowTaskDAO;
    
    @Override
    public List<FlowTaskVO> findNeedClaimTask(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedClaimTask(paramMap);
    }

    @Override
    public int findNeedClaimTaskCount(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedClaimTaskCount(paramMap);
    }

    @Override
    public List<FlowTaskVO> findNeedComplateTask(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedComplateTask(paramMap);
    }

    @Override
    public int findNeedComplateTaskCount(Map<String, Object> paramMap) {
        return workFlowTaskDAO.findNeedComplateTaskCount(paramMap);
    }


}
