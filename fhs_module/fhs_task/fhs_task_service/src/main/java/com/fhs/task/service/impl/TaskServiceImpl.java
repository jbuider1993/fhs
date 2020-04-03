package com.fhs.task.service.impl;

import com.fhs.task.mapper.TaskMapper;
import com.fhs.task.service.TaskService;
import com.fhs.task.vo.TaskVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 任务服务类
 */
@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper taskMapper;

    @Override
    public List<TaskVO> getTaskVO(TaskVO param, int start, int pageSize) {
        return taskMapper.getTaskVO(param,start,pageSize);
    }

    @Override
    public int getTaskCount(TaskVO param) {
        return taskMapper.getTaskCount(param);
    }
}
