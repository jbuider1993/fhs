package com.fhs.flow.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.flow.dox.FlowInstanceDO;
import com.fhs.flow.vo.FlowInstanceVO;

/**
 * fhs的流程实例，为activiti的实例扩展表(FlowInstance)表服务接口
 *
 * @author jackwong
 * @since 2019-11-11 19:40:44
 */
public interface FlowInstanceService extends BaseService<FlowInstanceVO, FlowInstanceDO>{

    /**
     * 进行中
     */
    int STATUS_RUNNING = 0;
    /**
     * 结束
     */
    int STATUS_END = 1;
    /**
     * 撤销申请
     */
    int STATUS_REVOKE = 2;
}