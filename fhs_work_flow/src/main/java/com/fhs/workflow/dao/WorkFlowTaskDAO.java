package com.fhs.workflow.dao;

import com.fhs.core.base.dao.BaseDao;
import com.fhs.workflow.bean.WorkFlowListener;
import com.mybatis.jpa.annotation.MapperDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * 任务dao
 * 主要用来查询任务使用，完成任务或者签收以及其他操作使用引擎自带的taskservice操作
 * @author wanglei
 *
 */
@Repository
public interface WorkFlowTaskDAO
{
    /**
     * 查询待签收任务
     * @param paramMap 参数map
     * @return 代签收map集合
     */
    List<Map<String,Object>> findNeedClaimTask(Map<String, Object> paramMap);
    
    /**
     * 查询待签收任务总数
     * @param paramMap 参数map
     * @return 代签收总数
     */
    int findNeedClaimTaskCount(Map<String, Object> paramMap);

    /**
     * 查询待办任务
     * @param paramMap 参数map
     * @return 代签收map集合
     */
    List<Map<String,Object>> findNeedComplateTask(Map<String, Object> paramMap);


    /**
     * 查询待办任务总数
     * @param paramMap 参数map
     * @return 代签收总数
     */
    int findNeedComplateTaskCount(Map<String, Object> paramMap);
}
