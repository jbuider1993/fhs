package com.fhs.task.mapper;

import com.fhs.task.vo.TaskVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper {

    /**
     * 根据过滤条件获取task 列表
     * @param param 过滤条件
     * @param start 开视行
     * @param pageSize 每页多少条数据
     * @return
     */
    List<TaskVO> getTaskVO(@Param("task") TaskVO param,@Param("start") int start,@Param("pageSize")int pageSize);

    /**
     * 获取task总数
     * @param param 过滤条件
     * @return
     */
    int getTaskCount(@Param("task") TaskVO param);
}
