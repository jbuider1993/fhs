package com.fhs.flow.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.fhs.basics.api.rpc.FeignServerApiService;
import com.fhs.basics.vo.SettMsMenuServerVO;
import com.fhs.basics.vo.UcenterMsUserVO;
import com.fhs.common.constant.Constant;
import com.fhs.common.utils.CheckUtils;
import com.fhs.common.utils.FileUtils;
import com.fhs.common.utils.StringUtil;
import com.fhs.core.base.controller.BaseController;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.core.config.EConfig;
import com.fhs.core.exception.ParamException;
import com.fhs.core.feign.autowired.annotation.AutowiredFhs;
import com.fhs.core.listener.register.DistributedListenerRegister;
import com.fhs.core.result.HttpResult;
import com.fhs.core.valid.checker.ParamChecker;
import com.fhs.flow.constant.FlowConstant;
import com.fhs.flow.dox.FlowInstanceDO;
import com.fhs.flow.dox.FlowTaskHistoryDO;
import com.fhs.flow.service.*;
import com.fhs.flow.vo.*;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.*;


/**
 * 个人工作
 *
 * @author wanglei
 */
@RestController
@AutowiredFhs
@RequestMapping("/ms/myWorks")
public class MyWorksController extends BaseController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private WorkFlowTaskService workFlowTaskService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FlowCoreService flowCoreService;

    @Autowired
    private BpmImageService bpmImageService;

    @Autowired
    private FlowInstanceService instanceService;

    @Autowired
    private FlowJbpmXmlService flowJbpmXmlService;

    @Autowired
    private DistributedListenerRegister distributedListenerRegister;


    @Autowired
    private FlowInstanceService flowInstanceService;

    @AutowiredFhs
    private FeignServerApiService serverApiService;

    @Autowired
    private FlowTaskHistoryService flowTaskHistoryService;

    @Autowired
    private FlowTaskHistoryService taskHistoryService;


    /**
     * 查询待完成的工作
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("getNeedComplateTask")
    public Pager<FlowTaskVO> getNeedComplateTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, Object> paramMap = super.getPageTurnNum();
        paramMap.put("loginUserId", this.getSessionuser(request).getUserId());
        List<FlowTaskVO> dataList = workFlowTaskService.findNeedComplateTask(paramMap);
        int count = workFlowTaskService.findNeedComplateTaskCount(paramMap);
        return new Pager(count, dataList);
    }

    /**
     * 委派
     *
     * @param taskId 任务id
     * @param userId 用户id
     * @return
     */
    @RequestMapping("delegate")
    @Transactional(rollbackFor = Exception.class)
    public HttpResult delegate(String taskId, String userId, String userName, HttpServletRequest request) {
        ParamChecker.isNotNullOrEmpty(taskId, "任务id不可为空");
        ParamChecker.isNotNullOrEmpty(userId, "用户id不可为空");
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        taskService.delegateTask(taskId, userId);

        //删除老数据
        taskHistoryService.deleteMp(new LambdaQueryWrapper<FlowTaskHistoryDO>().eq(FlowTaskHistoryDO::getTaskId, taskId).ne(FlowTaskHistoryDO::getResult, FlowConstant.RESULT_DELEGATE));
        FlowTaskHistoryDO history = taskHistoryService.buildFlowTaskHistory(task.getTaskDefinitionKey(), task.getProcessInstanceId());
        history.setTaskFinishTime(new Date());
        history.setTitle(task.getName());
        history.setStatus(FlowTaskHistoryService.STATUS_FINISH);
        history.setAssigneeUserId(task.getAssignee());
        history.setUseTime((int) (System.currentTimeMillis() - task.getCreateTime().getTime()) / 1000 / 60);
        history.preInsert(getSessionuser(request).getUserId());
        history.setResult(FlowConstant.RESULT_DELEGATE);
        history.setRemark("委派给" + userName);
        taskHistoryService.insertSelective(history);
        return HttpResult.success();
    }

    /**
     * 查询待签收的工作
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("getNeedClaimTask")
    public Pager<FlowTaskVO> getNeedClaimTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageSizeInfo pageSizeInfo = super.getPageSizeInfo();
        UcenterMsUserVO userVO = getSessionuser(request);
        userVO.setStart(pageSizeInfo.getPageStart());
        userVO.setPageSize(pageSizeInfo.getPageSize());
        List<FlowTaskVO> dataList = workFlowTaskService.findNeedClaimTask(userVO);
        int count = workFlowTaskService.findNeedClaimTaskCount(userVO);
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
        Task task = taskService.createTaskQuery().taskId(request.getParameter("taskId")).singleResult();
        if (task.getAssignee() == null) {
            taskService.claim(request.getParameter("taskId"), getSessionuser(request).getUserId());
        } else {
            throw new ParamException("任务已经被签收过");
        }
        return HttpResult.success(true);
    }

    /**
     * 是否被签收
     */
    @RequestMapping("isClaimTask")
    public HttpResult<Boolean> isclaimTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Task task = taskService.createTaskQuery().taskId(request.getParameter("taskId")).singleResult();
        // 判断任务是否已经被签收
        if (task.getAssignee() == null) {
            //return HttpResult.error("已被签收");
            return HttpResult.success(true);
        }
        return HttpResult.success(false);
    }

    /**
     * 获取session里面的user
     *
     * @param request 请求对象
     * @return session里面的user
     */
    private UcenterMsUserVO getSessionuser(HttpServletRequest request) {
        return (UcenterMsUserVO) request.getSession().getAttribute(Constant.SESSION_USER);
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
        Map<String, HistoryTaskVO> taskMap = new HashMap<>();
        HistoryTaskVO tempTask = null;
        //流程变量
        Map<String, Object> variablesMap = null;
        Set<String> keys = null;
        for (HistoricTaskInstance historicTaskInstance : list) {
            tempTask = new HistoryTaskVO();
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
     *
     * @param taskId 历史任务的taskid
     * @return 是否撤回成功
     */
    @RequestMapping("withdraw")
    public HttpResult<Boolean> withdraw(String taskId, HttpServletRequest request) throws Exception {
        flowCoreService.updateWithdraw(taskId, this.getSessionuser(request).getUserId(), super.getParameterMap());
        return HttpResult.success(true);
    }

    /**
     * 撤销申请
     *
     * @param taskId 历史任务的taskid
     * @return 是否撤回成功
     */
    @RequestMapping("revoke")
    public HttpResult<Boolean> revoke(String taskId, HttpServletRequest request) throws Exception {
        this.flowCoreService.updateEndSuccessProcess(taskId, super.getParameterMap(), true, this.getSessionuser(request).getUserId());
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
        ParamChecker.isNotNullOrEmpty(taskId, "taskid不能为空");
        return HttpResult.success(flowCoreService.findBackAvtivity(taskId));
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
        Map<String, Object> paramMap = super.getParameterMap();
        flowCoreService.updatePassProcess(taskId, paramMap);
        return HttpResult.success(true);
    }

    /**
     * 驳回
     *
     * @param taskId     任务id
     * @param isPre      驳回到上一任务，如果指定任务id为空并且这里设置为false的话则驳回到第一个任务
     * @param activityId 指定任务
     * @param request    请求
     * @return
     */
    @RequestMapping("backTask")
    @Transactional(rollbackFor = Exception.class)
    public HttpResult<Boolean> backProcess(String taskId, String activityId, boolean isPre, HttpServletRequest request) throws Exception {
        ParamChecker.isNotNullOrEmpty(taskId, "任务id不能为空");
        if (CheckUtils.isNullOrEmpty(activityId)) {
            List<BackAvtivityVO> activityList = flowCoreService.findBackAvtivity(taskId);
            if (activityList.isEmpty()) {
                throw new ParamException("当前任务不可驳回，因为无可驳回任务点");
            }
            // 获取上一个任务
            if (isPre) {
                activityId = activityList.get(activityList.size() - 1).getId();
                //获取第一个任务
            } else {
                activityId = activityList.get(0).getId();
            }
        }
        Map<String, Object> paramMap = super.getParameterMap();
        String instanceId = this.flowCoreService.findTaskById(taskId).getProcessInstanceId();
        flowCoreService.updateBackProcess(taskId, activityId, paramMap);
        Map<String, Object> msgMap = new HashMap<>();
        msgMap.put("instanceId", instanceId);
        int status = isPre ? FlowConstant.BUSINESS_INSTANCE_STATUS_APPROVAL : FlowConstant.BUSINESS_INSTANCE_STATUS_RESUBMIT;
        FlowInstanceVO instanceVO = flowInstanceService.findBean(FlowInstanceDO.builder().activitiProcessInstanceId(instanceId).build());
        instanceVO.setStatus(status);

        flowInstanceService.updateSelectiveById(instanceVO);
        msgMap.put("businessKey", instanceVO.getFormPkey());
        msgMap.put("instanceStatus", status);
        msgMap.put("type", FlowConstant.INSTANCE_NEWS_TYPE_BACK);
        FlowJbpmXmlVO xml = flowJbpmXmlService.selectById(instanceVO.getXmlId());
        ParamChecker.isNotNull(xml, "xml丢失");
        msgMap.put("namespace", xml.getNamespace());
        distributedListenerRegister.callListener(FlowConstant.LISTENER_INSTANCE, FlowConstant.ENVENT_NEWS, new Object(), msgMap);
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
        String instanceId = request.getParameter("instanceId");
        ParamChecker.isNotNullOrEmpty(instanceId, "实例id为必传");
        //获取历史流程实例
        HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(instanceId).singleResult();
        ParamChecker.isNotNullOrEmpty(processInstance, "实例id无效");
        String procDefId = processInstance.getProcessDefinitionId();
        InputStream imageStream = bpmImageService.draw(procDefId, instanceId);
        FileUtils.downloadInputStream(imageStream, response, "workflow.png");

    }


    /**
     * 根据id查询实例详情
     *
     * @param instanceId 工作流实例id
     * @return 工作流实例详情
     */
    @RequestMapping("findInstanceById")
    public void findInstanceById(String instanceId) {
        ParamChecker.isNotNull(instanceId, "instanceId为必传");
        FlowInstanceVO instance = this.instanceService.selectBean(FlowInstanceDO.builder().activitiProcessInstanceId(instanceId).build());
        ParamChecker.isNotNull(instance, "instanceId无效");
        InstanceVO result = new InstanceVO();
        BeanUtils.copyProperties(instance, result);
        FlowJbpmXmlVO xml = flowJbpmXmlService.selectById(instance.getXmlId());
        ParamChecker.isNotNull(xml, "流程xml被删除");

        result.setProcessKey(xml.getProcessKey());
        List<Task> tasks = this.flowCoreService.findTaskListByInstanceId(instanceId);
        result.setIsSubmiTask(Constant.INT_FALSE);
        result.setIsCanWithdraw(Constant.INT_FALSE);
        if (!tasks.isEmpty()) {
            // 如果不为空，如果task的id是第一个节点的话，那么就是提交节点,提交节点不允许撤回，其他的节点允许撤回
            if (tasks.get(0).getTaskDefinitionKey().equals(instance.getFirstDefinitionKey())) {
                result.setIsSubmiTask(Constant.INT_TRUE);
            } else {
                result.setIsCanWithdraw(Constant.INT_TRUE);
            }
        }
        String formUrl = null;

        HttpResult<SettMsMenuServerVO> menuServerVOHttpResut = serverApiService.getMenuServeById(xml.getServer());
        if (menuServerVOHttpResut.getCode() != Constant.SUCCESS_CODE) {
            throw new ParamException(menuServerVOHttpResut.getMessage());
        }
        String server = menuServerVOHttpResut.getData().getServerUrl();
        if (Constant.INT_TRUE == xml.getIsPagex()) {
            formUrl = server + "/ms/pagex/" + xml.getNamespace() + "_flow_handle.jsp?id=" + instance.getFormPkey();
        } else {
            formUrl = server + xml.getUri();
            formUrl = formUrl.contains("?") ? (formUrl + "&id=" + instance.getFormPkey()) : (formUrl + "?id=" + instance.getFormPkey());
        }
        JSONObject exParam = JSON.parseObject(instance.getExtFormParam());
        Set<String> keys = exParam.keySet();
        for (String key : keys) {
            formUrl = formUrl + key + "=" + exParam.getString(key);
        }
        result.setFormUrl(formUrl);
        super.outJsonp(HttpResult.success(result).asJson());
    }

}
