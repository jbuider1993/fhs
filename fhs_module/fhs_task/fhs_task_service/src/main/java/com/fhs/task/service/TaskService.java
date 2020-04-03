package com.fhs.task.service;

import com.fhs.task.vo.TaskVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务服务类
 */
public interface TaskService {
    /**
     * 根据过滤条件获取task 列表
     *
     * @param param    过滤条件
     * @param start    开视行
     * @param pageSize 每页多少条数据
     * @return
     */
    List<TaskVO> getTaskVO(TaskVO param, int start, int pageSize);

    /**
     * 获取task总数
     *
     * @param param 过滤条件
     * @return
     */
    int getTaskCount(TaskVO param);
}
