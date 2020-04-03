package com.fhs.flow.service.impl;

import com.fhs.core.base.service.impl.BaseServiceImpl;
import com.fhs.flow.dox.FlowListenerDO;
import com.fhs.flow.service.FlowListenerService;
import com.fhs.flow.vo.FlowListenerVO;
import org.springframework.stereotype.Service;

/**
 * 监听器(FlowListener)表服务实现类
 *
 * @author jackwong
 * @since 2019-11-11 14:28:44
 */
@Service("flowListenerService")
public class FlowListenerServiceImpl extends BaseServiceImpl<FlowListenerVO, FlowListenerDO> implements FlowListenerService {

}