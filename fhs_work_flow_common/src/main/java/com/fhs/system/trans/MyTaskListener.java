package com.fhs.system.trans;

import com.fhs.flow.form.TaskForm;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * 节点监听
 */
public class MyTaskListener implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {

        String eventName = delegateTask.getEventName();
        if ("create".endsWith(eventName)) {
            //任务创建并设置所有属性后触发。
            System.out.println("create=========");
        } else if ("assignment".endsWith(eventName)) {
            //任务分配给指定的人员时触发。当流程到达userTask， assignment事件会在create事件之前发生。
            System.out.println("assignment========" + delegateTask.getAssignee());
        } else if ("complete".endsWith(eventName)) {
            //任务完成，并尚未从运行数据中删除时触发。
            System.out.println("complete===========");
        } else if ("delete".endsWith(eventName)) {
            //只在任务删除之前发生。注意在通过completeTask正常完成时，也会执行。
            System.out.println("delete=============");
        }
    }

    private TaskForm structureForm(DelegateTask delegateTask){
        TaskForm taskForm = new TaskForm();
        taskForm.setId(delegateTask.getId());
        taskForm.setName(delegateTask.getName());
        taskForm.setDescription(delegateTask.getDescription());
        taskForm.setPriority(delegateTask.getPriority());
        taskForm.setProcessInstanceId(delegateTask.getProcessInstanceId());
        taskForm.setExecutionId(delegateTask.getExecutionId());
        taskForm.setProcessDefinitionId(delegateTask.getProcessDefinitionId());
        taskForm.setCreateTime(delegateTask.getCreateTime());
        taskForm.setTaskDefinitionKey(delegateTask.getTaskDefinitionKey());
        taskForm.setIsSuspended(delegateTask.isSuspended());
        taskForm.setTenantId(delegateTask.getTenantId());
        taskForm.setFormKey(delegateTask.getFormKey());
        taskForm.setEventName(delegateTask.getEventName());
        taskForm.setOwner(delegateTask.getOwner());
        taskForm.setAssignee(delegateTask.getAssignee());
        taskForm.setDueDate(delegateTask.getDueDate());
        taskForm.setCategory(delegateTask.getCategory());
        taskForm.setCandidates(delegateTask.getCandidates());
        return taskForm;
    }



}
