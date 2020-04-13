package com.fhs.flow.mapper;

import com.fhs.basics.vo.UcenterMsUserVO;
import com.fhs.flow.dox.FlowTaskDO;
import com.fhs.flow.vo.FlowTaskVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 任务dao
 * 主要用来查询任务使用，完成任务或者签收以及其他操作使用引擎自带的taskservice操作
 *
 * @author wanglei
 */
@Repository
public interface WorkFlowTaskMapper {
    /**
     * 查询待签收任务
     *
     * @param userVO 参数map
     * @return 代签收map集合
     */
    List<FlowTaskVO> findNeedClaimTask(UcenterMsUserVO userVO);

    /**
     * 查询待签收任务总数
     *
     * @param userVO 用户
     * @return 代签收总数
     */
    int findNeedClaimTaskCount(UcenterMsUserVO userVO);

    /**
     * 查询待办任务
     *
     * @param paramMap 参数map
     * @return 代签收map集合
     */
    List<FlowTaskVO> findNeedComplateTask(Map<String, Object> paramMap);


    /**
     * 查询待办任务总数
     *
     * @param paramMap 参数map
     * @return 代签收总数
     */
    int findNeedComplateTaskCount(Map<String, Object> paramMap);
}
