package com.fhs.flow.service.impl;

import com.fhs.basics.vo.UcenterMsUserVO;
import com.fhs.core.trans.service.impl.TransService;
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
 *
 * @author wanglei
 */
@Service("WorkFlowTaskServiceImpl")
public class WorkFlowTaskServiceImpl implements WorkFlowTaskService {

    @Autowired
    private WorkFlowTaskMapper workFlowTaskMapper;

    @Autowired
    private TransService transService;

    @Override
    public List<FlowTaskVO> findNeedClaimTask(UcenterMsUserVO userVO) {
        List<FlowTaskVO> result = workFlowTaskMapper.findNeedClaimTask(userVO);
        transService.transMore(result);
        return result;
    }

    @Override
    public int findNeedClaimTaskCount(UcenterMsUserVO userVO) {
        return workFlowTaskMapper.findNeedClaimTaskCount(userVO);
    }

    @Override
    public List<FlowTaskVO> findNeedComplateTask(Map<String, Object> paramMap) {
        List<FlowTaskVO> result = workFlowTaskMapper.findNeedComplateTask(paramMap);
        transService.transMore(result);
        return result;
    }

    @Override
    public int findNeedComplateTaskCount(Map<String, Object> paramMap) {
        return workFlowTaskMapper.findNeedComplateTaskCount(paramMap);
    }


}
