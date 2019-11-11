package com.fhs.workflow.dao;

import com.fhs.workflow.bean.FlowJbpmXml;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 流程列表-xml(FlowJbpmXml)表数据库访问层
 *
 * @author sb生成的代码
 * @since 2019-11-11 14:29:04
 */
@MapperDefinition(domainClass = FlowJbpmXml.class, orderBy = " update_time DESC")
public interface FlowJbpmXmlDao extends BaseDao<FlowJbpmXml> {

}