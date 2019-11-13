package com.fhs.workflow.service;

import com.fhs.workflow.bean.FlowInstance;
import java.util.List;
import com.fhs.core.base.service.BaseService;
/**
 * fhs的流程实例，为activiti的实例扩展表(FlowInstance)表服务接口
 *
 * @author jackwong
 * @since 2019-11-11 19:40:44
 */
public interface FlowInstanceService extends BaseService<FlowInstance>{

    /**
     * 进行中
     */
    int STATUS_RUNNING = 0;
    /**
     * 结束
     */
    int STATUS_END = 1;
    /**
     * 撤回
     */
    int STATUS_REVOKE = 2;
}