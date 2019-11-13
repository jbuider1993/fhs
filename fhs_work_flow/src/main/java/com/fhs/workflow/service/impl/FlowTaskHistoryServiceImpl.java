package com.fhs.workflow.service.impl;

import com.fhs.workflow.bean.FlowTaskHistory;
import com.fhs.workflow.dao.FlowTaskHistoryDao;
import com.fhs.workflow.service.FlowTaskHistoryService;
import org.springframework.stereotype.Service;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import java.util.List;

/**
 * 流程任务日志(FlowTaskHistory)表服务实现类
 *
 * @author jackwong
 * @since 2019-11-12 14:40:34
 */
@Service("flowTaskHistoryService")
public class FlowTaskHistoryServiceImpl extends BaseServiceImpl<FlowTaskHistory>   implements FlowTaskHistoryService {
    
}