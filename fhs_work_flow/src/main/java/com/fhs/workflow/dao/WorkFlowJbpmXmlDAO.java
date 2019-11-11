package com.fhs.workflow.dao;

import com.fhs.core.base.dao.BaseDao;
import com.fhs.workflow.bean.WorkFlowJbpmXml;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

/**
 * 流程xml管理
 * 
 * @author  wanglei
 * @version  [版本号, 2017/07/25 11:04:23]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository
@MapperDefinition(domainClass = WorkFlowJbpmXml.class, orderBy = " update_time DESC")
public interface WorkFlowJbpmXmlDAO extends BaseDao<WorkFlowJbpmXml>
{
}