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
@Repository
@MapperDefinition(domainClass = FlowTaskHistory.class, orderBy = " update_time DESC")
public interface FlowTaskHistoryDao extends BaseDao<FlowTaskHistory> {

    /**
     * 查询最后一个任务历史
     * @param instanceId 实例id
     * @return 最后一个任务历史
     */
    FlowTaskHistory findLastTaskHistory(@Param("instanceId") String instanceId);

    /**
     * 查询实例最大的ordernum
     * @param instanceId 实例id
     * @return 最大的ordernum、
     */
    Integer findMaxOrderNum(@Param("instanceId")String instanceId);

}