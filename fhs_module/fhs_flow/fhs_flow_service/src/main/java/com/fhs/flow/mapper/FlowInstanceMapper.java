package com.fhs.flow.mapper;


import com.fhs.core.base.mapper.FhsBaseMapper;
import com.fhs.flow.dox.FlowInstanceDO;
import com.mybatis.jpa.annotation.MapperDefinition;

/**
 * fhs的流程实例，为activiti的实例扩展表(FlowInstance)表数据库访问层
 *
 * @author jackwong
 * @since 2019-11-11 19:40:44
 */
@MapperDefinition(domainClass = FlowInstanceDO.class, orderBy = " update_time DESC")
public interface FlowInstanceMapper extends FhsBaseMapper<FlowInstanceDO> {

}