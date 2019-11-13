package com.fhs.workflow.service.impl;

import com.fhs.workflow.bean.FlowInstance;
import com.fhs.workflow.dao.FlowInstanceDao;
import com.fhs.workflow.service.FlowInstanceService;
import org.springframework.stereotype.Service;
import com.fhs.core.base.service.impl.BaseServiceImpl;
import javax.annotation.Resource;
import java.util.List;

/**
 * fhs的流程实例，为activiti的实例扩展表(FlowInstance)表服务实现类
 *
 * @author jackwong
 * @since 2019-11-11 19:40:44
 */
@Service("flowInstanceService")
public class FlowInstanceServiceImpl extends BaseServiceImpl<FlowInstance>   implements FlowInstanceService {
    
}