package com.fhs.task.controller;

import com.fhs.core.base.controller.BaseController;
import com.fhs.core.base.pojo.pager.Pager;
import com.fhs.core.exception.ParamException;
import com.fhs.core.result.HttpResult;
import com.fhs.logger.Logger;
import com.fhs.task.service.TaskService;
import com.fhs.task.vo.TaskVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * quartz在线管理控制器
 */
@RestController
@RequestMapping("/ms/task")
public class TaskController extends BaseController {
    private final static Logger LOGGER = Logger.getLogger(TaskController.class);


    @Autowired
    private Scheduler scheduler;

    @Autowired
    private TaskService taskService;

    /**
     * 新增
     * @param quartz
     * @return
     * @throws SchedulerException
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    @PostMapping("/add")
    @RequiresPermissions("task:add")
    public HttpResult<Boolean> save(@Validated  TaskVO quartz) throws SchedulerException {
        if(quartz.getOldJobGroup() != null) {
            JobKey key = new JobKey(quartz.getOldJobName(), quartz.getOldJobGroup());
            scheduler.deleteJob(key);
        }
        try{
            Class cls = Class.forName(quartz.getJobClassName());
            cls.newInstance();
            //构建job信息
            JobDetail job = JobBuilder.newJob(cls).withIdentity(quartz.getJobName(),
                    quartz.getJobGroup())
                    .withDescription(quartz.getDescription()).build();
            // 触发时间点
            CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartz.getCronExpression());
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger" + quartz.getJobName(), quartz.getJobGroup())
                    .startNow().withSchedule(cronScheduleBuilder).build();
            //交由Scheduler安排触发
            scheduler.scheduleJob(job, trigger);
        }catch (ClassNotFoundException e){
            throw new ParamException("类不存在");
        }catch (InstantiationException e){
            throw new ParamException("反射错误");
        }catch (IllegalAccessException e){
            throw new ParamException("反射错误");
        }
        return HttpResult.success(true);
    }

    /**
     * 获取列表
     * @param quartz
     * @return
     */
    @RequestMapping("/list")
    @RequiresPermissions("task:see")
    public Pager<TaskVO> list(TaskVO quartz) {
        PageSizeInfo pageSizeInfo = super.getPageSizeInfo();
        List<TaskVO> list = taskService.getTaskVO(quartz, pageSizeInfo.getPageStart(), pageSizeInfo.getPageSize());
        return new Pager<>(taskService.getTaskCount(quartz),list);
    }

    /**
     * 手动触发任务
     * @param quartz
     * @param response
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/trigger")
    @RequiresPermissions("task:update")
    public HttpResult<Boolean> trigger(TaskVO quartz, HttpServletResponse response) throws SchedulerException {
        JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.triggerJob(key);
        return HttpResult.success(true);
    }

    /**
     * 暂停执行任务
     * @param quartz
     * @param response
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/pause")
    @RequiresPermissions("task:update")
    public HttpResult pause(TaskVO quartz, HttpServletResponse response) throws SchedulerException {
        JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.pauseJob(key);
        return HttpResult.success();
    }

    /**
     * 恢复执行
     * @param quartz
     * @param response
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/resume")
    @RequiresPermissions("task:update")
    public HttpResult resume(TaskVO quartz, HttpServletResponse response) throws SchedulerException {
        JobKey key = new JobKey(quartz.getJobName(), quartz.getJobGroup());
        scheduler.resumeJob(key);
        return HttpResult.success();
    }

    /**
     * 删除任务
     * @param quartz
     * @param response
     * @return
     * @throws SchedulerException
     */
    @RequestMapping("/remove")
    @RequiresPermissions("task:del")
    public HttpResult remove(TaskVO quartz, HttpServletResponse response) throws SchedulerException {
        TriggerKey triggerKey = TriggerKey.triggerKey(quartz.getJobName(), quartz.getJobGroup());
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 移除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除任务
        scheduler.deleteJob(JobKey.jobKey(quartz.getJobName(), quartz.getJobGroup()));
        return HttpResult.success();
    }

}
