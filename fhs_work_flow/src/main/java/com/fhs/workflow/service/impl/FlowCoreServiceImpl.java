package com.fhs.workflow.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fhs.common.utils.*;
import com.fhs.core.exception.ParamChecker;
import com.fhs.core.exception.ParamException;
import com.fhs.workflow.bean.FlowInstance;
import com.fhs.workflow.bean.FlowJbpmXml;
import com.fhs.workflow.bean.FlowTask;
import com.fhs.workflow.bean.FlowTaskHistory;
import com.fhs.workflow.service.FlowCoreService;
import com.fhs.workflow.service.FlowInstanceService;
import com.fhs.workflow.service.FlowJbpmXmlService;
import com.fhs.workflow.service.FlowTaskHistoryService;
import com.fhs.workflow.vo.BackAvtivityVO;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.impl.task.TaskDefinition;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 工作流核心服务，工作流程流转控制服务
 * @author Jackwong
 * @date 2019 -11-12 11:48:02
 */
@Service
public class FlowCoreServiceImpl implements FlowCoreService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FlowJbpmXmlService flowJbpmXmlService;

    @Autowired
    private FlowInstanceService flowInstanceService;

    @Autowired
    private FlowTaskHistoryService taskHistoryService;

    @Override
    public String updateStartProcessInstanceByKey(String processDefinitionKey, String businessKey, Map<String, Object> variables, Map<String, Object> extFormParam, String userId) throws Exception {
        FlowJbpmXml flowJbpmXml = flowJbpmXmlService.selectBean(FlowJbpmXml.builder().processKey(processDefinitionKey).build());
        ParamChecker.isNotNullOrEmpty(flowJbpmXml, "processDefinitionKey无效");
        int version = flowJbpmXml.getStatus() == FlowJbpmXmlService.STATUS_HAS_DEPLOY ? flowJbpmXml.getVersion() : (flowJbpmXml.getVersion() - 1);
        if (version < 1) {
            throw new ParamException("流程未发布");
        }

        //自建工作流实例表
        FlowInstance flowInstance = new FlowInstance();
        flowInstance.setId(StringUtil.getUUID());
        flowInstance.setXmlId(flowJbpmXml.getId());
        flowInstance.setFormPkey(businessKey);
        flowInstance.setExtFormParam(JsonUtils.map2json(extFormParam));
        flowInstance.setTitle(variables.containsKey("title") ? ConverterUtils.toString(variables.get("title")) : flowJbpmXml.getName());
        flowInstance.setStatus(FlowInstanceService.STATUS_RUNNING);
        flowInstance.preInsert(userId);
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(flowJbpmXml.getProcessKey() + version, businessKey, variables);
        checkProProcessInstanceIsEnd(processInstance.getId(), null);
        flowInstance.setActivitiProcessInstanceId(processInstance.getId());
        flowInstanceService.insertJpa(flowInstance);
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(flowInstance.getActivitiProcessInstanceId()).list();
        FlowTaskHistory taskHistory = null;
        // 第一个节点是提交节点，自动通过
        for (Task task : tasks) {
            task.setAssignee(userId);
            taskService.complete(task.getId(), variables);
            taskHistory = FlowTaskHistory.builder().id(StringUtil.getUUID()).taskFinishTime(flowInstance.getCreateTime()).instanceId(flowInstance.getActivitiProcessInstanceId())
                    .title("提交").status(FlowTaskHistoryService.STATUS_FINISH).result(FlowTaskHistoryService.RESULT_SUBMIT).build();
            taskHistory.setOrderNum(1);
            taskHistory.setCode("001");
            taskHistory.setDefinitionKey(task.getTaskDefinitionKey());
            taskHistory.preInsert(userId);
            taskHistory.setAssigneeUserId(userId);
            taskHistory.setTaskId(task.getId());
            taskHistoryService.insertJpa(taskHistory);
            checkProProcessInstanceIsEnd(task.getProcessInstanceId(), null);
            flowInstanceService.updateSelectiveById(FlowInstance.builder().id(flowInstance.getId()).firstDefinitionKey(task.getTaskDefinitionKey()).build());
        }
        return flowInstance.getId();
    }


    /**
     * 根据当前任务ID，查询可以驳回的任务节点
     *
     * @param taskId 当前任务ID
     */
    @Override
    public List<BackAvtivityVO> findBackAvtivity(String taskId) throws Exception {
        Task task = this.findTaskById(taskId);
        /*
           1 获取实例所有的历史记录
           2 获取当前task的code  比如00129   001-2-9
           3 遍历获取所有的history code   task.code.startWith(item.code) 添加到集合中
         */
        // 根据排序字段获取所有的历史记录
        List<FlowTaskHistory> taskHistories = taskHistoryService.selectPageForOrder(FlowTaskHistory.builder().instanceId(task.getProcessInstanceId())
                .build(),0,Integer.MAX_VALUE," order_num ASC ");
        List<BackAvtivityVO> result = new ArrayList<>();
        // 获取当天task的 code 比如是 0012910 那么他的爸爸应该是001 0012 00129
        String taskCode = taskHistoryService.buildFlowTaskHistory(task.getTaskDefinitionKey(),task.getProcessInstanceId()).getCode();
        taskHistories.forEach(item->{
            if(taskCode.startsWith(item.getCode()) && (!taskCode.equals(item.getCode()))){
                result.add(BackAvtivityVO.builder().id(item.getDefinitionKey()).title(item.getTitle()).build());
            }
        });
        return result;
    }

    @Override
    public void updatePassProcess(String taskId, Map<String, Object> variables) throws Exception {
        variables.put("result",FlowTaskHistoryService.RESULT_PASS);
        List<Task> tasks = getChildTask(taskId);
        for (Task task : tasks) {// 级联结束本节点发起的会签任务
            commitProcess(task.getId(), null, null);
        }
        commitProcess(taskId, variables, null);
    }

    /**
     * 获取子任务
     *
     * @param parentTaskId 父任务id
     * @return 所有的字任务
     */
    private List<Task> getChildTask(String parentTaskId) throws Exception {
        Task task = this.findTaskById(parentTaskId);
        List<Task> allTask = taskService.createTaskQuery().processInstanceId(task.getProcessInstanceId()).list();
        return allTask.stream().filter(tempTask -> task.getId().equals(tempTask.getParentTaskId())).collect(Collectors.toList());
    }


    @Override
    public void updateBackProcess(String taskId, String activityId, Map<String, Object> variables) throws Exception {
        variables.put("result", FlowTaskHistoryService.RESULT_BACK);
        if (CheckUtils.isNullOrEmpty(activityId)) {
            throw new ParamException("驳回目标节点ID为空！");
        }

        // 查询本节点发起的会签任务，并结束
        List<Task> tasks = this.getChildTask(taskId);
        for (Task task : tasks) {
            commitProcess(task.getId(), null, null);
        }

        // 查找所有并行任务节点，同时驳回
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(
                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());
        for (Task task : taskList) {
            commitProcess(task.getId(), variables, activityId);
        }
    }


    /**
     * 撤回的时候，使用
     * @param taskId
     * @param activityId
     * @param variables
     * @throws Exception
     */
    private void callBackProcess(String taskId, String activityId, Map<String, Object> variables) throws Exception {
        variables.put("result", FlowTaskHistoryService.RESULT_WITHDRAW);
        if (CheckUtils.isNullOrEmpty(activityId)) {
            throw new ParamException("取回目标节点ID为空！");
        }
        // 查找所有并行任务节点，同时取回
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(
                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());
        for (Task task : taskList) {
            commitProcess(task.getId(), variables, activityId);
        }
    }



    @Override
    public void updateEndSuccessProcess(String taskId,Map<String, Object> variables,boolean isRevoke,String userId) throws Exception {
        ActivityImpl endActivity = findActivitiImpl(taskId, "end");
        if(isRevoke){
            variables.put("result",FlowTaskHistoryService.RESULT_REVOKE);
            Task task = this.findTaskById(taskId);
            FlowInstance flowInstance = this.flowInstanceService.selectBean(FlowInstance.builder().activitiProcessInstanceId(task.getProcessInstanceId()).build());
            ParamChecker.isNotNullOrEmpty(flowInstance,"task对应的 flowInstance 丢失,activitiProcessInstanceId:" + task.getProcessInstanceId());
            if(!userId.equals(flowInstance.getCreateUser())){
                throw new ParamException("您不是工单创建人，无法撤销");
            }
            variables.put("result",FlowTaskHistoryService.RESULT_REVOKE);
        }
        else{
            variables.put("result",FlowTaskHistoryService.RESULT_END);
        }
        commitProcess(taskId, null, endActivity.getId());
    }

    @Override
    public void updateJointProcess(String taskId, List<String> userIds) throws Exception {
        Task parentTask = findTaskById(taskId);
        for (String userId : userIds) {
            TaskEntity task = (TaskEntity) taskService.newTask(StringUtil.getUUID());
            task.setAssignee(userId);
            task.setName(parentTask.getName() + "-会签");
            task.setProcessDefinitionId(findProcessDefinitionEntityByTaskId(
                    taskId).getId());
            task.setProcessInstanceId(findProcessInstanceByTaskId(taskId)
                    .getId());
            task.setParentTaskId(taskId);
            task.setDescription("jointProcess");
            taskService.saveTask(task);
            FlowTaskHistory history = FlowTaskHistory.builder().id(StringUtil.getUUID())
                    .instanceId(task.getProcessInstanceId()).title(task.getName()).status(FlowTaskHistoryService.STATUS_TODO).assigneeUserId(task.getAssignee())
                    .build();
            taskHistoryService.insertJpa(history);
        }
    }

    @Override
    public void updateTransferAssignee(String taskId, String sourceUserId,String targetUserId) {
        Task task  = this.findTaskById(taskId);
        ParamChecker.isNotNullOrEmpty(task,"task不存在");
        if(!sourceUserId.equals(task.getAssignee())){
            throw  new ParamException("此任务处理人不是您");
        }
        taskService.setAssignee(taskId, targetUserId);
    }

    @Override
    public void updateWithdraw(String taskId, String userId, Map<String, Object> variables) throws Exception {
        HistoricTaskInstance currTask = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
        if(!userId.equals(currTask.getAssignee())){
            throw new ParamException("您无法撤回别人审批的任务");
        }
        //根据流程id查询代办任务中流程信息
        Task task = taskService.createTaskQuery().processInstanceId(currTask.getProcessInstanceId()).singleResult();
        if(task==null){
            throw new ParamException("流程已经结束，撤回失败");
        }
        //取回流程接点 当前任务id 取回任务id
       callBackProcess(task.getId(),currTask.getTaskDefinitionKey(),variables);
    }

    @Override
    public List<Task> findTaskListByInstanceId(String instanceId) {
        return  taskService.createTaskQuery().processInstanceId(instanceId).list();
    }

    /**
     * ***************************************************************************************************************************************************<br>
     * ************************************************以下为流程会签操作核心逻辑******************************************************************************<br>
     * ***************************************************************************************************************************************************<br>
     */

    /**
     * ***************************************************************************************************************************************************<br>
     * ************************************************以上为流程会签操作核心逻辑******************************************************************************<br>
     * ***************************************************************************************************************************************************<br>
     */

    /**
     * ***************************************************************************************************************************************************<br>
     * ************************************************以下为流程转向操作核心逻辑******************************************************************************<br>
     * ***************************************************************************************************************************************************<br>
     */

    /**
     * 驳回或者完成当前任务
     *
     * @param taskId     当前任务ID
     * @param variables  流程变量
     * @param activityId 流程转向执行任务节点ID<br>
     *                   此参数为空，默认为提交操作
     * @throws Exception
     */
    @Override
    public void commitProcess(String taskId, Map<String, Object> variables,
                              String activityId) throws Exception {
        if (variables == null) {
            variables = new HashMap<String, Object>();
        }
        Task task = this.findTaskById(taskId);
        FlowTaskHistory history = this.taskHistoryService.selectBean(FlowTaskHistory.builder().taskId(taskId).build());
        if (history != null) {
            history.setTaskFinishTime(DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN));
            history.setStatus(FlowTaskHistoryService.STATUS_FINISH);
            history.setUseTime((int) (System.currentTimeMillis() - task.getCreateTime().getTime()) / 1000 / 60);
            history.setRemark(ConverterUtils.toString(variables.get("remark")));
            history.setResult(ConverterUtils.toInteger(variables.get("result")));
            history.setTaskId(task.getId());
            taskHistoryService.updateById(history);
            //创建一个扔进去
        } else {
            history = this.taskHistoryService.buildFlowTaskHistory(task.getTaskDefinitionKey(),task.getProcessInstanceId());
            history.setTaskId(taskId);
            history.setTaskFinishTime((DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN)));
            history.setTitle(task.getName());
            history.setStatus(FlowTaskHistoryService.STATUS_FINISH);
            history.setAssigneeUserId(task.getAssignee());
            history.setUseTime((int) (System.currentTimeMillis() - task.getCreateTime().getTime()) / 1000 / 60);
            history.setRemark(ConverterUtils.toString(variables.get("remark")));
            history.setResult(ConverterUtils.toInteger(variables.get("result")));
            history.setCreateTime(DateUtils.formartDate(task.getCreateTime(), DateUtils.DATETIME_PATTERN));
            history.setTaskId(task.getId());
            taskHistoryService.insertJpa(history);
        }
        // 跳转节点为空，默认提交操作
        if (CheckUtils.isNullOrEmpty(activityId)) {
            taskService.complete(taskId, variables);
        } else {// 流程转向操作
            turnTransition(taskId, activityId, variables);
        }
        checkProProcessInstanceIsEnd(task.getProcessInstanceId(), null);
    }

    /**
     * 清空指定活动节点流向
     *
     * @param activityImpl 活动节点
     * @return 节点流向集合
     */
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        // 存储当前节点所有流向临时变量
        List<PvmTransition> oriPvmTransitionList = new ArrayList<PvmTransition>();
        // 获取当前节点所有流向，存储到临时变量，然后清空
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        return oriPvmTransitionList;
    }

    /**
     * 还原指定活动节点流向
     *
     * @param activityImpl         活动节点
     * @param oriPvmTransitionList 原有节点流向集合
     */
    private void restoreTransition(ActivityImpl activityImpl,
                                   List<PvmTransition> oriPvmTransitionList) {
        // 清空现有流向
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        pvmTransitionList.clear();
        // 还原以前流向
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
    }

    /**
     * 流程转向操作
     *
     * @param taskId     当前任务ID
     * @param activityId 目标节点任务ID
     * @param variables  流程变量
     * @throws Exception
     */
    private void turnTransition(String taskId, String activityId,
                                Map<String, Object> variables) throws Exception {
        // 当前节点
        ActivityImpl currActivity = findActivitiImpl(taskId, null);
        // 清空当前流向
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

        // 创建新流向
        TransitionImpl newTransition = currActivity.createOutgoingTransition();
        // 目标节点
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
        // 设置新流向的目标节点
        newTransition.setDestination(pointActivity);

        // 执行转向任务
        taskService.complete(taskId, variables);
        // 删除目标节点新流入
        pointActivity.getIncomingTransitions().remove(newTransition);

        // 还原以前流向
        restoreTransition(currActivity, oriPvmTransitionList);
    }

    /**
     * ***************************************************************************************************************************************************<br>
     * ************************************************以上为流程转向操作核心逻辑******************************************************************************<br>
     * ***************************************************************************************************************************************************<br>
     */

    /**
     * ***************************************************************************************************************************************************<br>
     * ************************************************以下为查询流程驳回节点核心逻辑***************************************************************************<br>
     * ***************************************************************************************************************************************************<br>
     */


    /**
     * 判断流程是否已经结束，如果已经结束则更新自定义的instance表
     *
     * @param processInstanceId 流程实例id
     */
    private void checkProProcessInstanceIsEnd(String processInstanceId, String taskId) {
        if (processInstanceId == null) {
            processInstanceId = this.findTaskById(taskId).getProcessInstanceId();
        }
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery().processInstanceId(processInstanceId)
                .singleResult();
        //代表结束了，就去更新instance状态
        if (processInstance == null) {
            FlowInstance instance = this.flowInstanceService.findBean(FlowInstance.builder().activitiProcessInstanceId(processInstanceId).build());
            if (instance != null) {
                List<FlowTaskHistory> histories = this.taskHistoryService.selectPageForOrder(FlowTaskHistory.builder().instanceId(processInstanceId).build(),0,1,"create_time DESC");
                if(!histories.isEmpty()){
                    //看最后一个任务的结果，如果是撤回的话，那么实例状态就为撤回
                   Integer finalTaskResult =  histories.get(0).getResult();
                   if(FlowTaskHistoryService.RESULT_REVOKE == finalTaskResult){
                       this.flowInstanceService.updateSelectiveById(FlowInstance.builder().id(instance.getId()).status(FlowInstanceService.STATUS_REVOKE).build());
                       return;
                   }
                }
                this.flowInstanceService.updateSelectiveById(FlowInstance.builder().id(instance.getId()).status(FlowInstanceService.STATUS_END).build());
            }
        }
    }


    /**
     * 根据当前节点，查询输出流向是否为并行终点，如果为并行终点，则拼装对应的并行起点ID
     *
     * @param activityImpl 当前节点
     * @return
     */
    private String findParallelGatewayId(ActivityImpl activityImpl) {
        List<PvmTransition> incomingTransitions = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : incomingTransitions) {
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
            activityImpl = transitionImpl.getDestination();
            String type = (String) activityImpl.getProperty("type");
            if ("parallelGateway".equals(type)) {// 并行路线
                String gatewayId = activityImpl.getId();
                String gatewayType = gatewayId.substring(gatewayId
                        .lastIndexOf("_") + 1);
                if ("END".equals(gatewayType.toUpperCase())) {
                    return gatewayId.substring(0, gatewayId.lastIndexOf("_"))
                            + "_start";
                }
            }
        }
        return null;
    }

    /**
     * 根据流入任务集合，查询最近一次的流入任务节点
     *
     * @param processInstance 流程实例
     * @param tempList        流入任务集合
     * @return
     */
    private ActivityImpl filterNewestActivity(ProcessInstance processInstance,
                                              List<ActivityImpl> tempList) {
        while (tempList.size() > 0) {
            ActivityImpl activity_1 = tempList.get(0);
            HistoricActivityInstance activityInstance_1 = findHistoricUserTask(
                    processInstance, activity_1.getId());
            if (activityInstance_1 == null) {
                tempList.remove(activity_1);
                continue;
            }

            if (tempList.size() > 1) {
                ActivityImpl activity_2 = tempList.get(1);
                HistoricActivityInstance activityInstance_2 = findHistoricUserTask(
                        processInstance, activity_2.getId());
                if (activityInstance_2 == null) {
                    tempList.remove(activity_2);
                    continue;
                }

                if (activityInstance_1.getEndTime().before(
                        activityInstance_2.getEndTime())) {
                    tempList.remove(activity_1);
                } else {
                    tempList.remove(activity_2);
                }
            } else {
                break;
            }
        }
        if (tempList.size() > 0) {
            return tempList.get(0);
        }
        return null;
    }

    /**
     * 查询指定任务节点的最新记录
     *
     * @param processInstance 流程实例
     * @param activityId
     * @return
     */
    private HistoricActivityInstance findHistoricUserTask(
            ProcessInstance processInstance, String activityId) {
        HistoricActivityInstance rtnVal = null;
        // 查询当前流程实例审批结束的历史节点
        List<HistoricActivityInstance> historicActivityInstances = historyService
                .createHistoricActivityInstanceQuery().activityType("userTask")
                .processInstanceId(processInstance.getId()).activityId(
                        activityId).finished()
                .orderByHistoricActivityInstanceEndTime().desc().list();
        if (historicActivityInstances.size() > 0) {
            rtnVal = historicActivityInstances.get(0);
        }

        return rtnVal;
    }

    /**
     * *******************************************************************************************************<br>
     * ********************************以上为查询流程驳回节点核心逻辑***********************************************<br>
     * ********************************************************************************************************<br>
     */


    /**
     * 根据任务ID获得任务实例
     *
     * @param taskId 任务ID
     * @return
     * @throws Exception
     */
    private TaskEntity findTaskById(String taskId) {
        TaskEntity task = (TaskEntity) taskService.createTaskQuery().taskId(
                taskId).singleResult();
        if (task == null) {
            throw new ParamException("任务实例未找到!");
        }
        return task;
    }

    /**
     * 根据流程实例ID和任务key值查询所有同级任务集合
     *
     * @param processInstanceId
     * @param key
     * @return
     */
    private List<Task> findTaskListByKey(String processInstanceId, String key) {
        return taskService.createTaskQuery().processInstanceId(
                processInstanceId).taskDefinitionKey(key).list();
    }

    /**
     * 根据任务ID获取对应的流程实例
     *
     * @param taskId 任务ID
     * @return
     * @throws Exception
     */
    private ProcessInstance findProcessInstanceByTaskId(String taskId) {
        // 找到流程实例
        ProcessInstance processInstance = runtimeService
                .createProcessInstanceQuery().processInstanceId(
                        findTaskById(taskId).getProcessInstanceId())
                .singleResult();
        if (processInstance == null) {
            throw new ParamException("流程实例未找到!");
        }
        return processInstance;
    }

    /**
     * 根据任务ID获取流程定义
     *
     * @param taskId 任务ID
     * @return
     * @throws Exception
     */
    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
            String taskId) {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(findTaskById(taskId)
                        .getProcessDefinitionId());

        if (processDefinition == null) {
            throw new ParamException("流程定义未找到!");
        }

        return processDefinition;
    }

    /**
     * 根据任务ID和节点ID获取活动节点 <br>
     *
     * @param taskId     任务ID
     * @param activityId 活动节点ID <br>
     *                   如果为null或""，则默认查询当前活动节点 <br>
     *                   如果为"end"，则查询结束节点 <br>
     * @return
     * @throws Exception
     */
    @Override
    public ActivityImpl findActivitiImpl(String taskId, String activityId)
            throws Exception {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

        // 获取当前活动节点ID
        if (CheckUtils.isNullOrEmpty(activityId)) {
            activityId = findTaskById(taskId).getTaskDefinitionKey();
        }

        // 根据流程定义，获取该流程实例的结束节点
        if (activityId.toUpperCase().equals("END")) {
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {
                List<PvmTransition> pvmTransitionList = activityImpl
                        .getOutgoingTransitions();
                if (pvmTransitionList.isEmpty()) {
                    return activityImpl;
                }
            }
        }

        // 根据节点ID，获取对应的活动节点
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
                .findActivity(activityId);

        return activityImpl;
    }

    /**
     * ********************************************************************************<br>
     * **********************以上为根据 任务节点ID 获取流程各对象查询方法**********************<br>
     * *********************************************************************************<br>
     */

    /**
     * 获取下一个用户任务信息
     *
     * @param taskId 任务Id信息
     * @return 下一个用户任务用户组信息
     * @throws Exception
     */
    public TaskDefinition getNextTaskInfo(String taskId) throws Exception {

        ProcessDefinitionEntity processDefinitionEntity = null;

        String id = null;

        TaskDefinition task = null;

        //获取流程实例Id信息
        String processInstanceId = taskService.createTaskQuery().taskId(taskId).singleResult().getProcessInstanceId();

        //获取流程发布Id信息
        String definitionId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();

        processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(definitionId);

        ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        //当前流程节点Id信息
        String activitiId = execution.getActivityId();

        //获取流程所有节点信息
        List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();

        //遍历所有节点信息
        for (ActivityImpl activityImpl : activitiList) {
            id = activityImpl.getId();
            if (activitiId.equals(id)) {
                //获取下一个节点信息
                task = nextTaskDefinition(activityImpl, activityImpl.getId(), null, processInstanceId);
                break;
            }
        }
        return task;
    }

    /**
     * 下一个任务节点信息,
     * <p>
     * 如果下一个节点为用户任务则直接返回,
     * <p>
     * 如果下一个节点为排他网关, 获取排他网关Id信息, 根据排他网关Id信息和execution获取流程实例排他网关Id为key的变量值,
     * 根据变量值分别执行排他网关后线路中的el表达式, 并找到el表达式通过的线路后的用户任务
     *
     * @param activityImpl      流程节点信息
     * @param activityId        当前流程节点Id信息
     * @param elString          排他网关顺序流线段判断条件
     * @param processInstanceId 流程实例Id信息
     * @return
     */
    private TaskDefinition nextTaskDefinition(ActivityImpl activityImpl, String activityId, String elString, String processInstanceId) {

        PvmActivity ac = null;

        Object s = null;

        // 如果遍历节点为用户任务并且节点不是当前节点信息
        if ("userTask".equals(activityImpl.getProperty("type")) && !activityId.equals(activityImpl.getId())) {
            // 获取该节点下一个节点信息
            TaskDefinition taskDefinition = ((UserTaskActivityBehavior) activityImpl.getActivityBehavior())
                    .getTaskDefinition();
            return taskDefinition;
        } else {
            // 获取节点所有流向线路信息
            List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
            List<PvmTransition> outTransitionsTemp = null;
            for (PvmTransition tr : outTransitions) {
                ac = tr.getDestination(); // 获取线路的终点节点
                // 如果流向线路为排他网关
                if ("exclusiveGateway".equals(ac.getProperty("type"))) {
                    outTransitionsTemp = ac.getOutgoingTransitions();

                    // 如果网关路线判断条件为空信息
                    if (CheckUtils.isNullOrEmpty(elString)) {
                        // 获取流程启动时设置的网关判断条件信息
                        elString = getGatewayCondition(ac.getId(), processInstanceId);
                    }

                    // 如果排他网关只有一条线路信息
                    if (outTransitionsTemp.size() == 1) {
                        return nextTaskDefinition((ActivityImpl) outTransitionsTemp.get(0).getDestination(), activityId,
                                elString, processInstanceId);
                    } else if (outTransitionsTemp.size() > 1) { // 如果排他网关有多条线路信息
                        for (PvmTransition tr1 : outTransitionsTemp) {
                            s = tr1.getProperty("conditionText"); // 获取排他网关线路判断条件信息
                            // 判断el表达式是否成立
                            if (isCondition(ac.getId(), StringUtil.trim(s.toString()), elString)) {
                                return nextTaskDefinition((ActivityImpl) tr1.getDestination(), activityId, elString,
                                        processInstanceId);
                            }
                        }
                    }
                } else if ("userTask".equals(ac.getProperty("type"))) {
                    return ((UserTaskActivityBehavior) ((ActivityImpl) ac).getActivityBehavior()).getTaskDefinition();
                } else {
                }
            }
            return null;
        }
    }

    /**
     * 查询流程启动时设置排他网关判断条件信息
     *
     * @param gatewayId         排他网关Id信息, 流程启动时设置网关路线判断条件key为网关Id信息
     * @param processInstanceId 流程实例Id信息
     * @return
     */
    public String getGatewayCondition(String gatewayId, String processInstanceId) {
        Execution execution = runtimeService.createExecutionQuery().processInstanceId(processInstanceId).singleResult();
        Object object = runtimeService.getVariable(execution.getId(), gatewayId);
        return object == null ? "" : object.toString();
    }

    /**
     * 根据key和value判断el表达式是否通过信息
     *
     * @param key   el表达式key信息
     * @param el    el表达式信息
     * @param value el表达式传入值信息
     * @return
     */
    public boolean isCondition(String key, String el, String value) {
        ExpressionFactory factory = new ExpressionFactoryImpl();
        SimpleContext context = new SimpleContext();
        context.setVariable(key, factory.createValueExpression(value, String.class));
        ValueExpression e = factory.createValueExpression(context, el, boolean.class);
        return (Boolean) e.getValue(context);
    }
}
