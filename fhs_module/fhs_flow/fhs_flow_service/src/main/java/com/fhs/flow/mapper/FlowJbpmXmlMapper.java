package com.fhs.flow.mapper;

import com.fhs.core.base.mapper.FhsBaseMapper;
import com.fhs.flow.dox.FlowJbpmXmlDO;
import com.mybatis.jpa.annotation.MapperDefinition;

/**
 * 流程列表-xml(FlowJbpmXml)表数据库访问层
 *
 * @author jackwong
 * @since 2019-11-11 14:29:04
 */
@MapperDefinition(domainClass = FlowJbpmXmlDO.class, orderBy = " update_time DESC")
public interface FlowJbpmXmlMapper extends FhsBaseMapper<FlowJbpmXmlDO> {

}