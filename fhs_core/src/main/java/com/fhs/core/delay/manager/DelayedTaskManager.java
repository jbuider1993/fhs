package com.fhs.core.delay.manager;

import com.fhs.common.utils.ConverterUtils;
import com.fhs.common.utils.Logger;
import com.fhs.core.config.EConfig;
import com.fhs.core.delay.fi.DelayedTask;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 延迟队列管理器
 */
@Component
public class DelayedTaskManager implements Runnable {

    private static final Logger LOG = Logger.getLogger(DelayedTaskManager.class);

    /**
     * 线程池,默认为2个
     */
    private ExecutorService exec = Executors.newFixedThreadPool(ConverterUtils.toInt(EConfig.getOtherConfigPropertiesValue("delayedTaskThreads"), 2));

    private DelayQueue<DelayedTaskHandle> queue;

    DelayedTaskManager() {
        queue = new DelayQueue<DelayedTaskHandle>();
        exec.execute(this);
    }

    @Override
    public void run() {
        while (true) {
            //从延迟队列中取值,如果没有对象过期则队列一直等待，
            DelayedTaskHandle handle = null;
            try {

                handle = queue.take();
                if (handle != null) {
                    handle.getTask().doTask(handle.getParamMap());
                }
            } catch (Exception e) {
                if(handle!=null)
                {
                    LOG.error("执行任务出错:" + handle.getTask() + ",param:" + handle.getParamMap(),e);
                }
                else{
                    LOG.error("执行任务出错",e);
                }
            }
        }
    }


    /**
     * 添加任务，
     * time 延迟时间-秒
     * task 任务
     * 用户为问题设置延迟时间
     */
    public void addTask(DelayedTask task, long delayTime, Map<String,Object> paramMap) {
        //创建一个任务
        DelayedTaskHandle handle = new DelayedTaskHandle(task, delayTime,paramMap);
        //将任务放在延迟的队列中
        queue.put(handle);
    }

}

/**
 * 任务处理器
 */
@Data
class DelayedTaskHandle implements Delayed {

    private DelayedTask task; // 消息内容
    private long trigger;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。
    private Map<String,Object> paramMap;//任务执行的时候需要的参数
    public DelayedTaskHandle(DelayedTask task, long delayTime, Map<String,Object> paramMap) {
        this.task = task;
        this.trigger = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
        this.paramMap = paramMap;
    }

    // 自定义实现比较方法返回 1 0 -1三个参数
    @Override
    public int compareTo(Delayed delayed) {
        // TODO Auto-generated method stub
        DelayedTaskHandle that = (DelayedTaskHandle) delayed;
        if (this.trigger > that.trigger)
            return 1;
        if (this.trigger < that.trigger)
            return -1;
        return 0;
    }

    // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), java.util.concurrent.TimeUnit.NANOSECONDS);
    }
}