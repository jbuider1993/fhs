package com.fhs.system.trans;

import com.fhs.flow.form.ExecutionForm;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;

/**
 * 全局监听器，连线监听
 */
public class MyExecutionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution delegateExecution) throws Exception {
        ExecutionForm executionForm = structureForm(delegateExecution);
        String eventName = delegateExecution.getEventName();
        if ("start".equals(eventName)) {
            System.out.println("start=========");
        } else if ("end".equals(eventName)) {
            System.out.println("end=========");
        } else if ("take".equals(eventName)) {
            //take是监控连线的时候使用的。
            System.out.println("take=========");
        }
    }

    private ExecutionForm structureForm(DelegateExecution delegateExecution) {
        /**
         * 可获取到的值
         */
        ExecutionForm executionForm = new ExecutionForm();
        executionForm.setId(delegateExecution.getId());
        executionForm.setEventName(delegateExecution.getEventName());
        executionForm.setBusinessKey(delegateExecution.getBusinessKey());
        executionForm.setCurrentActivityId(delegateExecution.getCurrentActivityId());
        executionForm.setCurrentActivityName(delegateExecution.getCurrentActivityName());
        executionForm.setParentId(delegateExecution.getParentId());
        executionForm.setProcessBusinessKey(delegateExecution.getProcessBusinessKey());
        executionForm.setProcessDefinitionId(delegateExecution.getProcessDefinitionId());
        executionForm.setProcessInstanceId(delegateExecution.getProcessInstanceId());
        executionForm.setTenantId(delegateExecution.getTenantId());
        executionForm.setSuperExecutionId(delegateExecution.getSuperExecutionId());

/*        //获取审批人信息 start
        String activityId = delegateExecution.getCurrentActivityId();
        String processDefinitionId=delegateExecution.getProcessDefinitionId(); // 获取流程定义id

        ProcessDefinitionEntity processDefinitionEntity=(ProcessDefinitionEntity) delegateExecution.getEngineServices().getRepositoryService()
                .getProcessDefinition(processDefinitionId);

        ActivityImpl activityImpl=processDefinitionEntity.findActivity(activityId); // 根据活动id获取活动实例
        TaskDefinition taskDef = (TaskDefinition)activityImpl.getProperties().get("taskDefinition");
        String zpr = taskDef.getAssigneeExpression()==null?"":taskDef.getAssigneeExpression().getExpressionText();//代理人
        Set<Expression> userCodes = taskDef.getCandidateUserIdExpressions();//候选人
        Set<Expression> roleCodes = taskDef.getCandidateGroupIdExpressions();//候选组
        //获取审批人信息 end*/

        return executionForm;
    }


}

