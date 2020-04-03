package com.fhs.flow.mapper;

import com.fhs.core.base.mapper.FhsBaseMapper;
import com.fhs.flow.dox.WorkFlowJbpmXmlDO;
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
@MapperDefinition(domainClass = WorkFlowJbpmXmlDO.class, orderBy = " update_time DESC")
public interface WorkFlowJbpmXmlMapper extends FhsBaseMapper<WorkFlowJbpmXmlDO>
{
}