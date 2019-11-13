package com.fhs.workflow.dao;

import com.fhs.workflow.bean.FlowTaskHistory;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.fhs.core.base.dao.BaseDao;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 流程任务日志(FlowTaskHistory)表数据库访问层
 *
 * @author jackwong
 * @since 2019-11-12 14:40:34
 */
@MapperDefinition(domainClass = FlowTaskHistory.class, orderBy = " update_time DESC")
public interface FlowTaskHistoryDao extends BaseDao<FlowTaskHistory> {

}