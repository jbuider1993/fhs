package com.fhs.flow.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.flow.dox.FlowJbpmXmlDO;
import com.fhs.flow.vo.FlowJbpmXmlVO;

/**
 * 流程列表-xml(FlowJbpmXml)表服务接口
 *
 * @author jackwong
 * @since 2019-11-11 14:29:04
 */
public interface FlowJbpmXmlService extends BaseService<FlowJbpmXmlVO, FlowJbpmXmlDO>{

    /**
     * 已发布
     */
    int STATUS_HAS_DEPLOY = 1;

    /**
     * 发布工作流到引擎中，将状态改为已发布
     * @param xmlId 工作流配置对象
     */
    void releaseWorkFlow(String xmlId);
}