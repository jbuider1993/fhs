package com.fhs.flow.service;


/**
 * 扩展的会签服务
 *
 * @author Jackwong
 * @date 2019 -11-11 19:45:07
 */
public interface FlowJointTaskService {
    /**
     * 判断一个任务是否为会签任务
     *
     * @param taskId taskid
     * @return true会签任务false非会签任务
     */
    boolean isJointTask(String taskId);
}
