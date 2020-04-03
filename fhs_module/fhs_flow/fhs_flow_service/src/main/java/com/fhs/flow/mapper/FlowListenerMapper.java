package com.fhs.flow.mapper;

import com.fhs.core.base.mapper.FhsBaseMapper;
import com.fhs.flow.dox.FlowListenerDO;
import com.mybatis.jpa.annotation.MapperDefinition;

/**
 * 监听器(FlowListener)表数据库访问层
 *
 * @author jackwong
 * @since 2019-11-11 14:28:44
 */
@MapperDefinition(domainClass = FlowListenerDO.class, orderBy = " update_time DESC")
public interface FlowListenerMapper extends FhsBaseMapper<FlowListenerDO> {

}