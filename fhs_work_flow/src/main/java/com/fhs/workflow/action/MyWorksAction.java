package com.fhs.workflow.action;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.FileUtils;
import com.fhs.core.base.action.BaseAction;
import com.fhs.core.exception.ParamChecker;
import com.fhs.core.exception.ParamException;
import com.fhs.core.page.Pager;
import com.fhs.core.result.HttpResult;
import com.fhs.ucenter.api.vo.SysUserVo;
import com.fhs.workflow.bean.FlowTask;
import com.fhs.workflow.bean.HistoryTask;
import com.fhs.workflow.service.BpmImageService;
import com.fhs.workflow.service.FlowCoreService;
import com.fhs.workflow.service.WorkFlowTaskService;
import com.fhs.workflow.vo.BackAvtivityVO;
import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
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

    @Autowired
    private BpmImageService bpmImageService;


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
     * 撤回审批的任务
     * @param taskId 历史任务的taskid
     * @return 是否撤回成功
     */
    @RequestMapping("withdraw")
    public HttpResult<Boolean> withdraw(String taskId,HttpServletRequest request) throws Exception {
        flowCoreService.updateWithdraw(taskId,this.getSessionuser(request).getUserId(),super.getParameterMap(request));
        return HttpResult.success(true);
    }

    /**
     * 撤销申请
     * @param taskId 历史任务的taskid
     * @return 是否撤回成功
     */
    @RequestMapping("revoke")
    public HttpResult<Boolean> revoke(String taskId,HttpServletRequest request) throws Exception {
        this.flowCoreService.updateEndSuccessProcess(taskId,super.getParameterMap(request),true,this.getSessionuser(request).getUserId());
        // 撤销申请
        return HttpResult.success(true);
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
     * @param taskId  任务id
     * @param isPre 驳回到上一任务，如果指定任务id为空并且这里设置为false的话则驳回到第一个任务
     * @param activityId 指定任务
     * @param request 请求
     * @return
     */
    @RequestMapping("backTask")
    public HttpResult<Boolean> backProcess(String taskId, String activityId,boolean isPre, HttpServletRequest request) throws Exception {
        ParamChecker.isNotNullOrEmpty(taskId, "任务id不能为空");
        if(CheckUtils.isNullOrEmpty(activityId)){
            List<ActivityImpl> activityList = flowCoreService.findBackAvtivity(taskId);
            if(activityList.isEmpty()){
                throw  new ParamException("当前任务不可驳回，因为无可驳回任务点");
            }
            // 获取上一个任务
            if(isPre){
                activityId = activityList.get(activityList.size()-1).getId();

            //获取第一个任务
            }else{
                activityId = activityList.get(0).getId();
            }
        }
        Map<String, Object> paramMap = super.getParameterMap(request);
        flowCoreService.updateBackProcess(taskId, activityId, paramMap);
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
        ParamChecker.isNotNullOrEmpty(processInstance,"实例id无效");
        String procDefId = processInstance.getProcessDefinitionId();
        InputStream imageStream = bpmImageService.draw(procDefId,processInstanceId);
        FileUtils.downloadInputStream(imageStream,response,"workflow.png");

    }




}
