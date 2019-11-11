package com.fhs.workflow.dao;

import com.fhs.core.base.dao.BaseDao;
import com.fhs.workflow.bean.WorkFlowInstance;
import com.fhs.workflow.bean.WorkFlowJbpmXml;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

/**
 * 自建工作流实例
 * 
 * @author  wanglei
 * @version  [版本号, 2017/07/31 12:26:34]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
@Repository
@MapperDefinition(domainClass = WorkFlowInstance.class, orderBy = " update_time DESC")
public interface WorkFlowInstanceDAO extends BaseDao<WorkFlowInstance>
{
    
}