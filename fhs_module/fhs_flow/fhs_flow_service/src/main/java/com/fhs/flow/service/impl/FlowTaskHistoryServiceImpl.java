package com.fhs.flow.service.impl;

import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.core.trans.service.impl.TransService;
import com.fhs.flow.dox.FlowTaskHistoryDO;
import com.fhs.flow.mapper.FlowTaskHistoryMapper;
import com.fhs.flow.service.FlowTaskHistoryService;
import com.fhs.flow.vo.FlowTaskHistoryVO;
import com.fhs.flow.vo.TaskHistoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 流程任务日志(FlowTaskHistory)表服务实现类
 *
 * @author jackwong
 * @since 2019-11-12 14:40:34
 */
@Service("flowTaskHistoryService")
public class FlowTaskHistoryServiceImpl extends BaseServiceImpl<FlowTaskHistoryVO, FlowTaskHistoryDO> implements FlowTaskHistoryService {

    @Autowired
    private FlowTaskHistoryMapper taskHistoryMapper;

    @Autowired
    private TransService transService;

    @Override
    public FlowTaskHistoryVO buildFlowTaskHistory(String definitionKey, String instanceId) {
        /*
         1 先看看有么有definitionKey一样的兄弟，如果有的话，用他的code和order
         2 如果没有的话，那么我是一个新节点，我的爸爸是最后一个节点(order最大的那个)
         */
        List<FlowTaskHistoryVO> brotherHitorys = super.findForList(FlowTaskHistoryDO.builder().instanceId(instanceId).definitionKey(definitionKey).build());
        if (!brotherHitorys.isEmpty()) {
            return d2v(FlowTaskHistoryVO.builder().id(StringUtil.getUUID()).orderNum(brotherHitorys.get(0).getOrderNum()).definitionKey(definitionKey)
                    .code(brotherHitorys.get(0).getCode()).instanceId(instanceId).build());
        }
        FlowTaskHistoryDO lastTaskHistory = taskHistoryMapper.findLastTaskHistory(instanceId);
        if (lastTaskHistory == null) {
            return d2v(FlowTaskHistoryVO.builder().id(StringUtil.getUUID()).orderNum(1).definitionKey(definitionKey)
                    .code("001").build());
        }
        int maxOrderNum = taskHistoryMapper.findMaxOrderNum(instanceId);
        maxOrderNum++;
        return d2v(FlowTaskHistoryDO.builder().id(StringUtil.getUUID()).orderNum(maxOrderNum).definitionKey(definitionKey)
                .code(lastTaskHistory.getCode() + maxOrderNum).instanceId(instanceId).build());
    }

    @Override
    public List<TaskHistoryVO> findTaskHistoryList(Map<String, Object> paramMap) {
        List<TaskHistoryVO> result = taskHistoryMapper.findTaskHistoryList(paramMap);
        transService.transMore(result);
        return result;
    }

    @Override
    public int findTaskHistoryCount(Map<String, Object> paramMap) {
        return taskHistoryMapper.findTaskHistoryCount(paramMap);
    }

    @Override
    public List<TaskHistoryVO> findApprovalRecord(String instanceId) {
        return taskHistoryMapper.findApprovalRecord(instanceId);
    }

}