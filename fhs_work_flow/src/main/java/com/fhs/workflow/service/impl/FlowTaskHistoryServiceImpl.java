package com.fhs.workflow.service.impl;

import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.workflow.bean.FlowTaskHistory;
import com.fhs.workflow.dao.FlowTaskHistoryDao;
import com.fhs.workflow.service.FlowTaskHistoryService;
import com.fhs.workflow.vo.TaskHistoryVO;
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
public class FlowTaskHistoryServiceImpl extends BaseServiceImpl<FlowTaskHistory>   implements FlowTaskHistoryService {

    @Autowired
    private FlowTaskHistoryDao taskHistoryDao;

    @Override
    public FlowTaskHistory buildFlowTaskHistory(String definitionKey, String instanceId) {
        /*
         1 先看看有么有definitionKey一样的兄弟，如果有的话，用他的code和order
         2 如果没有的话，那么我是一个新节点，我的爸爸是最后一个节点(order最大的那个)
         */
        List<FlowTaskHistory>  brotherHitorys = super.findForList(FlowTaskHistory.builder().instanceId(instanceId).definitionKey(definitionKey).build());
        if(!brotherHitorys.isEmpty()){
            return FlowTaskHistory.builder().id(StringUtil.getUUID()).orderNum(brotherHitorys.get(0).getOrderNum()).definitionKey(definitionKey)
                    .code(brotherHitorys.get(0).getCode()).instanceId(instanceId).build();
        }
        FlowTaskHistory lastTaskHistory = taskHistoryDao.findLastTaskHistory(instanceId);
        if(lastTaskHistory==null){
            return FlowTaskHistory.builder().id(StringUtil.getUUID()).orderNum(1).definitionKey(definitionKey)
                    .code("001").build();
        }
        int maxOrderNum = taskHistoryDao.findMaxOrderNum(instanceId);
        maxOrderNum++;
        return FlowTaskHistory.builder().id(StringUtil.getUUID()).orderNum(maxOrderNum).definitionKey(definitionKey)
                .code(lastTaskHistory.getCode()+maxOrderNum).instanceId(instanceId).build();
    }

    @Override
    public List<TaskHistoryVO> findTaskHistoryList(Map<String, Object> paramMap) {
        return taskHistoryDao.findTaskHistoryList(paramMap);
    }

    @Override
    public int findTaskHistoryCount(Map<String, Object> paramMap) {
        return taskHistoryDao.findTaskHistoryCount(paramMap);
    }

    @Override
    public List<TaskHistoryVO> findApprovalRecord(String instanceId) {
        return taskHistoryDao.findApprovalRecord(instanceId);
    }

}