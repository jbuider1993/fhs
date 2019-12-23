package com.fhs.workflow.dao;

import com.fhs.core.base.dao.BaseDao;
import com.fhs.workflow.bean.FlowTaskHistory;
import com.fhs.workflow.vo.TaskHistoryVO;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询已办纪录
     * @param paramMap
     * @return
     */
    List<TaskHistoryVO> findTaskHistoryList(Map<String, Object> paramMap);

    /**
     * 查询已办纪录数量
     * @param paramMap
     * @return
     */
    int findTaskHistoryCount(Map<String, Object> paramMap);

    /**
     * 查询审批历史
     * @param instanceId
     * @return
     */
    List<TaskHistoryVO> findApprovalRecord(@Param("instanceId") String instanceId);

}