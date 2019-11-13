package com.fhs.workflow.service.impl;

import com.fhs.common.utils.*;
import com.fhs.core.exception.ParamChecker;
import com.fhs.core.exception.ParamException;
import com.fhs.workflow.bean.FlowInstance;
import com.fhs.workflow.bean.FlowJbpmXml;
import com.fhs.workflow.bean.FlowTaskHistory;
import com.fhs.workflow.bean.WorkFlowJbpmXml;
import com.fhs.workflow.service.*;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
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
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Flow core service
 *
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
        flowInstance.preInsert(userId);
        ProcessInstance processInstance = this.runtimeService.startProcessInstanceByKey(flowJbpmXml.getProcessKey() + version, businessKey, variables);
        flowInstance.setActivitiProcessInstanceId(processInstance.getId());
        FlowTaskHistory taskHistory = FlowTaskHistory.builder().id(StringUtil.getUUID()).taskFinishTime(flowInstance.getCreateTime()).instanceId(flowInstance.getActivitiProcessInstanceId())
                .title("开始节点").status(FlowTaskHistoryService.STATUS_FINISH).build();
        taskHistory.preInsert(userId);
        taskHistory.setAssigneeUserId(userId);
        flowInstanceService.insertJpa(flowInstance);
        taskHistoryService.insertJpa(taskHistory);
        List<Task> tasks = taskService.createTaskQuery().processInstanceId(flowInstance.getActivitiProcessInstanceId()).list();
        // 第一个节点是提交节点，自动通过
        for (Task task : tasks) {
            task.setAssignee(userId);
            taskService.complete(task.getId(), variables);
            taskHistory = FlowTaskHistory.builder().id(StringUtil.getUUID()).taskFinishTime(flowInstance.getCreateTime()).instanceId(flowInstance.getActivitiProcessInstanceId())
                    .title("提交").status(FlowTaskHistoryService.STATUS_FINISH).build();
            taskHistory.preInsert(userId);
            taskHistory.setAssigneeUserId(userId);
            taskHistoryService.insertJpa(taskHistory);
        }
        return flowInstance.getId();
    }


    /**
     * 根据当前任务ID，查询可以驳回的任务节点
     *
     * @param taskId 当前任务ID
     */
    @Override
    public List<ActivityImpl> findBackAvtivity(String taskId) throws Exception {
        List<ActivityImpl> rtnList = null;
        Task task = this.findTaskById(taskId);
        ParamChecker.isNotNullOrEmpty(task, "taskId无效");
        if (task.getParentTaskId() != null) {// 会签任务节点，不允许驳回
            rtnList = new ArrayList<ActivityImpl>();
        } else {
            rtnList = iteratorBackActivity(taskId, findActivitiImpl(taskId,
                    null), new ArrayList<ActivityImpl>(),
                    new ArrayList<ActivityImpl>());
        }
        return reverList(rtnList);
    }

    @Override
    public void updatePassProcess(String taskId, Map<String, Object> variables) throws Exception {
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

    @Override
    public void updateCallBackProcess(String taskId, String activityId) throws Exception {
        if (CheckUtils.isNullOrEmpty(activityId)) {
            throw new ParamException("驳回目标节点ID为空！");
        }
        // 查找所有并行任务节点，同时取回
        List<Task> taskList = findTaskListByKey(findProcessInstanceByTaskId(
                taskId).getId(), findTaskById(taskId).getTaskDefinitionKey());
        for (Task task : taskList) {
            commitProcess(task.getId(), null, activityId);
        }
    }

    @Override
    public void updateEndSuccessProcess(String taskId) throws Exception {
        ActivityImpl endActivity = findActivitiImpl(taskId, "end");
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
    public void updateTransferAssignee(String taskId, String userId) {
        taskService.setAssignee(taskId, userId);
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
        // 跳转节点为空，默认提交操作
        if (CheckUtils.isNullOrEmpty(activityId)) {
            Task task = this.findTaskById(taskId);
            taskService.complete(taskId, variables);
            FlowTaskHistory history = this.taskHistoryService.selectBean(FlowTaskHistory.builder().taskId(taskId).build());
            if (history != null) {
                history.setTaskFinishTime(DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN));
                history.setStatus(FlowTaskHistoryService.STATUS_FINISH);
                history.setUseTime((int) (System.currentTimeMillis() - task.getCreateTime().getTime()) / 1000 / 60);
                history.setRemark(ConverterUtils.toString(variables.get("remark")));
                history.setResult(ConverterUtils.toInteger(variables.get("result")));
                taskHistoryService.updateById(history);
                //创建一个扔进去
            } else {
                history = FlowTaskHistory.builder().id(StringUtil.getUUID()).taskFinishTime((DateUtils.getCurrentDateStr(DateUtils.DATETIME_PATTERN)))
                        .instanceId(task.getProcessInstanceId()).title(task.getName()).status(FlowTaskHistoryService.STATUS_FINISH).assigneeUserId(task.getAssignee())
                        .build();
                history.setUseTime((int) (System.currentTimeMillis() - task.getCreateTime().getTime()) / 1000 / 60);
                history.setRemark(ConverterUtils.toString(variables.get("remark")));
                history.setResult(ConverterUtils.toInteger(variables.get("result")));
                history.setCreateTime(DateUtils.formartDate(task.getCreateTime(), DateUtils.DATETIME_PATTERN));
                taskHistoryService.insertJpa(history);
            }
        } else {// 流程转向操作
            turnTransition(taskId, activityId, variables);
        }
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
     * 迭代循环流程树结构，查询当前节点可驳回的任务节点
     *
     * @param taskId       当前任务ID
     * @param currActivity 当前活动节点
     * @param rtnList      存储回退节点集合
     * @param tempList     临时存储节点集合（存储一次迭代过程中的同级userTask节点）
     * @return 回退节点集合
     */
    private List<ActivityImpl> iteratorBackActivity(String taskId,
                                                    ActivityImpl currActivity, List<ActivityImpl> rtnList,
                                                    List<ActivityImpl> tempList) throws Exception {
        // 查询流程定义，生成流程树结构
        ProcessInstance processInstance = findProcessInstanceByTaskId(taskId);

        // 当前节点的流入来源
        List<PvmTransition> incomingTransitions = currActivity
                .getIncomingTransitions();
        // 条件分支节点集合，userTask节点遍历完毕，迭代遍历此集合，查询条件分支对应的userTask节点
        List<ActivityImpl> exclusiveGateways = new ArrayList<ActivityImpl>();
        // 并行节点集合，userTask节点遍历完毕，迭代遍历此集合，查询并行节点对应的userTask节点
        List<ActivityImpl> parallelGateways = new ArrayList<ActivityImpl>();
        // 遍历当前节点所有流入路径
        for (PvmTransition pvmTransition : incomingTransitions) {
            TransitionImpl transitionImpl = (TransitionImpl) pvmTransition;
            ActivityImpl activityImpl = transitionImpl.getSource();
            String type = (String) activityImpl.getProperty("type");
            /**
             * 并行节点配置要求：<br>
             * 必须成对出现，且要求分别配置节点ID为:XXX_start(开始)，XXX_end(结束)
             */
            if ("parallelGateway".equals(type)) {// 并行路线
                String gatewayId = activityImpl.getId();
                String gatewayType = gatewayId.substring(gatewayId
                        .lastIndexOf("_") + 1);
                if ("START".equals(gatewayType.toUpperCase())) {// 并行起点，停止递归
                    return rtnList;
                } else {// 并行终点，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
                    parallelGateways.add(activityImpl);
                }
            } else if ("startEvent".equals(type)) {// 开始节点，停止递归
                return rtnList;
            } else if ("userTask".equals(type)) {// 用户任务
                tempList.add(activityImpl);
            } else if ("exclusiveGateway".equals(type)) {// 分支路线，临时存储此节点，本次循环结束，迭代集合，查询对应的userTask节点
                currActivity = transitionImpl.getSource();
                exclusiveGateways.add(currActivity);
            }
        }

        /**
         * 迭代条件分支集合，查询对应的userTask节点
         */
        for (ActivityImpl activityImpl : exclusiveGateways) {
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
        }

        /**
         * 迭代并行集合，查询对应的userTask节点
         */
        for (ActivityImpl activityImpl : parallelGateways) {
            iteratorBackActivity(taskId, activityImpl, rtnList, tempList);
        }

        /**
         * 根据同级userTask集合，过滤最近发生的节点
         */
        currActivity = filterNewestActivity(processInstance, tempList);
        if (currActivity != null) {
            // 查询当前节点的流向是否为并行终点，并获取并行起点ID
            String id = findParallelGatewayId(currActivity);
            if (CheckUtils.isNullOrEmpty(id)) {// 并行起点ID为空，此节点流向不是并行终点，符合驳回条件，存储此节点
                rtnList.add(currActivity);
            } else {// 根据并行起点ID查询当前节点，然后迭代查询其对应的userTask任务节点
                currActivity = findActivitiImpl(taskId, id);
            }

            // 清空本次迭代临时集合
            tempList.clear();
            // 执行下次迭代
            iteratorBackActivity(taskId, currActivity, rtnList, tempList);
        }
        return rtnList;
    }

    /**
     * 反向排序list集合，便于驳回节点按顺序显示
     *
     * @param list
     * @return
     */
    private List<ActivityImpl> reverList(List<ActivityImpl> list) {
        List<ActivityImpl> rtnList = new ArrayList<ActivityImpl>();
        // 由于迭代出现重复数据，排除重复
        for (int i = list.size(); i > 0; i--) {
            if (!rtnList.contains(list.get(i - 1))) {
                rtnList.add(list.get(i - 1));
            }
        }
        return rtnList;
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
     * @param  taskId     任务Id信息
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
