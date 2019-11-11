package com.fhs.workflow.service;

import com.fhs.workflow.bean.FlowJbpmXml;
import java.util.List;
import com.fhs.core.base.service.BaseService;
/**
 * 流程列表-xml(FlowJbpmXml)表服务接口
 *
 * @author sb生成的代码
 * @since 2019-11-11 14:29:04
 */
public interface FlowJbpmXmlService extends BaseService<FlowJbpmXml>{

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