package com.fhs.workflow.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fhs.base.action.ModelSuperAction;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.core.base.action.BaseAction;
import com.fhs.core.exception.ParamChecker;
import com.fhs.core.page.Pager;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.vo.SysUserVo;
import com.fhs.workflow.bean.FlowTask;
import com.fhs.workflow.bean.HistoryTask;
import com.fhs.workflow.service.FlowCoreService;
import com.fhs.workflow.service.WorkFlowTaskService;
import com.fhs.workflow.vo.BackAvtivityVO;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.image.ProcessDiagramGenerator;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 个人工作
 *
 * @author wanglei
 */
@RestController
@RequestMapping("/ms/myWorks")
public class MyWorksAction extends BaseAction {

    @Autowired
    private TaskService taskService;

    @Autowired
    private WorkFlowTaskService workFlowTaskService;

    @Autowired
    RepositoryService repositoryService;
    @Autowired
    ManagementService managementService;
    @Autowired
    protected RuntimeService runtimeService;
    @Autowired
    ProcessEngineConfiguration processEngineConfiguration;
    @Autowired
    ProcessEngineFactoryBean processEngine;
    @Autowired
    HistoryService historyService;

    @Autowired
    private FlowCoreService flowCoreService;


    /**
     * 查询待完成的工作
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("getNeedComplateTask")
    public Pager<FlowTask> getNeedComplateTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> paramMap = super.getPageTurnNum(request);
        paramMap.put("loginUserId", this.getSessionuser(request).getUserId());
        List<FlowTask> dataList = workFlowTaskService.findNeedComplateTask(paramMap);
        int count = workFlowTaskService.findNeedComplateTaskCount(paramMap);
        return new Pager(count, dataList);
    }

    /**
     * 查询待签收的工作
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("getNeedClaimTask")
    public Pager<FlowTask> getNeedClaimTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> paramMap = super.getPageTurnNum(request);
        List<FlowTask> dataList = workFlowTaskService.findNeedClaimTask(paramMap);
        int count = workFlowTaskService.findNeedClaimTaskCount(paramMap);
        return new Pager(count, dataList);
    }

    /**
     * 签收任务
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("claimTask")
    public HttpResult<Boolean> claimTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
        taskService.claim(request.getParameter("taskId"), getSessionuser(request).getUserLoginName());
        return HttpResult.success(true);
    }

    /**
     * 获取session里面的user
     *
     * @param request 请求对象
     * @return session里面的user
     */
    private SysUserVo getSessionuser(HttpServletRequest request) {
        return (SysUserVo) request.getSession().getAttribute(Constant.SESSION_USER);
    }


    /**
     * 获取历史任务
     *
     * @param processInstanceId 流程实例id
     * @throws Exception
     */
    @RequestMapping("getHistoryTask")
    public Pager<Map<String, Object>> getHistoryTask(String processInstanceId) throws Exception {
        ParamChecker.isNotNullOrEmpty(processInstanceId, "流程实例id不能为空");
        List<HistoricTaskInstance> list = historyService
                .createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                .list();

        Map<String, HistoryTask> taskMap = new HashMap<>();
        HistoryTask tempTask = null;
        //流程变量
        Map<String, Object> variablesMap = null;
        Set<String> keys = null;
        for (HistoricTaskInstance historicTaskInstance : list) {
            tempTask = new HistoryTask();
            tempTask.setHistoricTaskInstance(historicTaskInstance);
            taskMap.put(historicTaskInstance.getExecutionId(), tempTask);
            variablesMap = historicTaskInstance.getProcessVariables();
            keys = variablesMap.keySet();
            for (String key : keys) {
                if (variablesMap.get(key) != null) {
                    tempTask.getVariable().append(key + ":" + variablesMap.get(key) + ",");
                }
            }
        }
        return new Pager(list.size(), list);
    }

    /**
     * 查找可回退节点
     *
     * @param taskId 任务id
     * @return
     */
    @RequestMapping("findBackAvtivity")
    public HttpResult<List<BackAvtivityVO>> findBackAvtivity(String taskId) throws Exception {
        ParamChecker.isNotNullOrEmpty(taskId,"taskid不能为空");
        List<ActivityImpl> activityList = flowCoreService.findBackAvtivity(taskId);
        List<BackAvtivityVO> result = new ArrayList<>();
        for (ActivityImpl temp : activityList) {
            result.add(BackAvtivityVO.builder().title(ConverterUtils.toString(temp.getProperty("name"))).id(temp.getId()).build());
        }
        return HttpResult.success(result);
    }

    /**
     * 完成任务
     *
     * @param taskId  任务id
     * @param request 请求
     * @return
     */
    @RequestMapping("complateTask")
    public HttpResult<Boolean> complateTask(String taskId, HttpServletRequest request) throws Exception {
        ParamChecker.isNotNullOrEmpty(taskId, "任务id不能为空");
        Map<String, Object> paramMap = super.getParameterMap(request);
        flowCoreService.updatePassProcess(taskId, paramMap);
        return HttpResult.success(true);
    }

    /**
     * 驳回
     *
     * @param taskId  任务id
     * @param request 请求
     * @return
     */
    @RequestMapping("backTask")
    public HttpResult<Boolean> backProcess(String taskId, String activityId, HttpServletRequest request) throws Exception {
        ParamChecker.isNotNullOrEmpty(taskId, "任务id不能为空");
       // ParamChecker.isNotNullOrEmpty(activityId, "目标节点id不能为空");
        Map<String, Object> paramMap = super.getParameterMap(request);
        List<ActivityImpl> list = flowCoreService.findBackAvtivity(taskId);
        //flowCoreService.updateBackProcess(taskId, activityId, paramMap);
        return HttpResult.success(true);
    }


    /**
     * 获取工作流图片
     *
     * @param request  request
     * @param response response
     * @throws Exception
     */
    @RequestMapping("getWorkFlowImg")
    public void getWorkFlowImg(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //processInstanceId
        String processInstanceId = request.getParameter("processInstanceId");
        ParamChecker.isNotNullOrEmpty(processInstanceId,"实例id为必传");
        //获取历史流程实例
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ParamChecker.isNotNullOrEmpty(processInstanceId,"实例id无效");
        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processInstance.getProcessDefinitionId());
        processEngineConfiguration = processEngine.getProcessEngineConfiguration();
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        ProcessDefinitionEntity definitionEntity = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(processInstance.getProcessDefinitionId());

        List<HistoricActivityInstance> highLightedActivitList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).list();
        //高亮环节id集合
        List<String> highLightedActivitis = new ArrayList<String>();
        //高亮线路id集合
        List<String> highLightedFlows = getHighLightedFlows(definitionEntity, highLightedActivitList);

        for (HistoricActivityInstance tempActivity : highLightedActivitList) {
            String activityId = tempActivity.getActivityId();
            highLightedActivitis.add(activityId);
        }
        InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", highLightedActivitis, highLightedFlows, processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(), null, null, 1.0);

        // 输出资源内容到相应对象
        byte[] b = new byte[1024];
        int len;
        while ((len = imageStream.read(b, 0, 1024)) != -1) {
            response.getOutputStream().write(b, 0, len);
        }
    }


    /**
     * 获取需要高亮的线
     * 这段是copy的
     *
     * @param processDefinitionEntity
     * @param historicActivityInstances
     * @return
     */
    private List<String> getHighLightedFlows(
            ProcessDefinitionEntity processDefinitionEntity,
            List<HistoricActivityInstance> historicActivityInstances) {

        List<String> highFlows = new ArrayList<String>();// 用以保存高亮的线flowId
        for (int i = 0; i < historicActivityInstances.size() - 1; i++) {// 对历史流程节点进行遍历
            ActivityImpl activityImpl = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i)
                            .getActivityId());// 得到节点定义的详细信息
            List<ActivityImpl> sameStartTimeNodes = new ArrayList<ActivityImpl>();// 用以保存后需开始时间相同的节点
            ActivityImpl sameActivityImpl1 = processDefinitionEntity
                    .findActivity(historicActivityInstances.get(i + 1)
                            .getActivityId());
            // 将后面第一个节点放在时间相同节点的集合里
            sameStartTimeNodes.add(sameActivityImpl1);
            for (int j = i + 1; j < historicActivityInstances.size() - 1; j++) {
                HistoricActivityInstance activityImpl1 = historicActivityInstances
                        .get(j);// 后续第一个节点
                HistoricActivityInstance activityImpl2 = historicActivityInstances
                        .get(j + 1);// 后续第二个节点
                if (activityImpl1.getStartTime().equals(
                        activityImpl2.getStartTime())) {
                    // 如果第一个节点和第二个节点开始时间相同保存
                    ActivityImpl sameActivityImpl2 = processDefinitionEntity
                            .findActivity(activityImpl2.getActivityId());
                    sameStartTimeNodes.add(sameActivityImpl2);
                } else {
                    // 有不相同跳出循环
                    break;
                }
            }
            List<PvmTransition> pvmTransitions = activityImpl
                    .getOutgoingTransitions();// 取出节点的所有出去的线
            for (PvmTransition pvmTransition : pvmTransitions) {
                // 对所有的线进行遍历
                ActivityImpl pvmActivityImpl = (ActivityImpl) pvmTransition
                        .getDestination();
                // 如果取出的线的目标节点存在时间相同的节点里，保存该线的id，进行高亮显示
                if (sameStartTimeNodes.contains(pvmActivityImpl)) {
                    highFlows.add(pvmTransition.getId());
                }
            }
        }
        return highFlows;
    }


}
