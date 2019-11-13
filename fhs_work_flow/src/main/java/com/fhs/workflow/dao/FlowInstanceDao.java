package com.fhs.workflow.dao;

import com.fhs.workflow.bean.FlowInstance;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * fhs的流程实例，为activiti的实例扩展表(FlowInstance)表数据库访问层
 *
 * @author jackwong
 * @since 2019-11-11 19:40:44
 */
@MapperDefinition(domainClass = FlowInstance.class, orderBy = " update_time DESC")
public interface FlowInstanceDao extends BaseDao<FlowInstance> {

}