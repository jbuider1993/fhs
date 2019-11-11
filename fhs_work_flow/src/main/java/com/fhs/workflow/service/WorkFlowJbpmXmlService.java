package com.fhs.workflow.service;

import com.fhs.core.base.service.BaseService;
import com.fhs.workflow.bean.WorkFlowJbpmXml;

/**
 * 流程xml管理
 * 
 * @author  wanglei
 * @version  [版本号, 2017/07/25 11:04:23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface WorkFlowJbpmXmlService extends BaseService<WorkFlowJbpmXml>
{
    /**
     * 发布工作流到引擎中，将状态改为已发布
     * @param xmlId 工作流配置对象
     */
    void releaseWorkFlow(String xmlId);
 

}