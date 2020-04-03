package com.fhs.flow.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.flow.dox.FlowInstanceDO;
import com.fhs.flow.service.FlowInstanceService;
import com.fhs.flow.vo.FlowInstanceVO;
import org.springframework.stereotype.Service;

/**
 * fhs的流程实例，为activiti的实例扩展表(FlowInstance)表服务实现类
 *
 * @author jackwong
 * @since 2019-11-11 19:40:44
 */
@Service("flowInstanceService")
public class FlowInstanceServiceImpl extends BaseServiceImpl<FlowInstanceVO, FlowInstanceDO> implements FlowInstanceService {

}