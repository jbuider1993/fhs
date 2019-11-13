package com.fhs.workflow.service;

import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;

import java.util.List;
import java.util.Map;

/**
 * activiti工作流实例核心服务
 * @author Jackwong
 * @date 2019 -11-11 19:45:07
 */
public interface FlowCoreService {

    /**
     * 根据流程引擎key启动一个流程
     * @param processDefinitionKey 流程key，系统会自动判断最新的一个流程图启动
     * @param businessKey 业务主键
     * @param variables 流程变量
     * @param extFormParam 扩展表单信息 --在查看详情的时候会给表单页面带上，不宜过多
     *  @param userId 创建用户id
     * @return 流程实例id
     */
    String updateStartProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables,
                                     Map<String,Object> extFormParam,String userId)throws Exception ;


    /**
     * @param taskId     当前任务ID
     * @param variables  流程变量
     * @param activityId 流程转向执行任务节点ID<br>
     *                   此参数为空，默认为提交操作
     * @throws Exception
     */
    void commitProcess(String taskId, Map<String, Object> variables,
                              String activityId) throws Exception;

    /**
     * 根据任务ID和节点ID获取活动节点 <br>
     *
     * @param taskId
     *            任务ID
     * @param activityId
     *            活动节点ID <br>
     *            如果为null或""，则默认查询当前活动节点 <br>
     *            如果为"end"，则查询结束节点 <br>
     *
     * @return
     * @throws Exception
     */
    ActivityImpl findActivitiImpl(String taskId, String activityId)throws Exception ;

    /**
     * 根据当前任务ID，查询可以驳回的任务节点
     *
     * @param taskId
     *            当前任务ID
     */
    List<ActivityImpl> findBackAvtivity(String taskId) throws Exception;

    /**
     * 审批通过(驳回直接跳回功能需后续扩展)
     *
     * @param taskId
     *            当前任务ID
     * @param variables
     *            流程存储参数
     * @throws Exception
     */
     void updatePassProcess(String taskId, Map<String, Object> variables)throws Exception ;

    /**
     * 驳回流程
     *
     * @param taskId
     *            当前任务ID
     * @param activityId
     *            驳回节点ID
     * @param variables
     *            流程存储参数
     * @throws Exception
     */
    void updateBackProcess(String taskId, String activityId,
                            Map<String, Object> variables) throws Exception;

    /**
     * 取回流程
     *
     * @param taskId
     *            当前任务ID
     * @param activityId
     *            取回节点ID
     * @throws Exception
     */
    void updateCallBackProcess(String taskId, String activityId)throws Exception ;

    /**
     * 办结流程(特权人直接审批通过等)
     *
     * @param taskId
     */
    void updateEndSuccessProcess(String taskId) throws Exception;

    /**
     * 会签操作
     *
     * @param taskId
     *            当前任务ID
     * @param userIds
     *            会签人账号集合
     * @throws Exception
     */
    void updateJointProcess(String taskId, List<String> userIds)throws Exception ;

    /**
     * 转办流程
     *
     * @param taskId
     *            当前任务节点ID
     * @param userId
     *            被转办人Code
     */
    void updateTransferAssignee(String taskId, String userId);
}
