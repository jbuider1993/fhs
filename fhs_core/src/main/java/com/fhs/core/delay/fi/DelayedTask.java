package com.fhs.core.delay.fi;


import java.util.Map;

/**
 * 延迟任务
 */
@FunctionalInterface
public interface DelayedTask {
    /**
     * 延迟任务执行方法
     * @param param 参数
     */
    void doTask(Map<String,Object> param);
}
