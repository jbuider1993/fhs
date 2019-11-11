package com.fhs.workflow.service;

import com.fhs.common.utils.EMap;

/**
 * 公共的工作流引擎服务
 * @author wanglei
 *
 */
public interface PubWorkFlowService {
    
    /**
     * 启动一个工作流，将表单保存到数据库中
     * 1 给自建工作流实例表添加一条数据
     * 2 给流程引擎添加数据(注意流程变量处理)
     * 3 保存表单数据
     * 4 更新自建流程实例数据
     * @param paramMap 包含表单数据 流程id 用户信息
     * @return businesskey
     */
    String startFlowForPubForm(EMap<String, Object> paramMap)throws Exception ;
    
    /**
     * 完成任务
     * 1 记录表单数据
     * 2 完成任务
     * 3 将流程变量缓存
     * 4 更新自建流程实例表
     * @param paramMap 包含完成任务的表单信息taskid buinesskey 流程实例id
     * @return 表单id
     */
    String complateTask(EMap<String, Object> paramMap)  throws Exception;
    
    
}
