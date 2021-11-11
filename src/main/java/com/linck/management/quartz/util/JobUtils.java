package com.linck.management.quartz.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-09-16 23:18
 **/
@Slf4j
public class JobUtils {

    /**
     * 运行状态的 Job 的 JobKey 记录下来，用于停止 Job 时使用
     */
    private static Map<Class, JobDetail> jobDetailMap = new HashMap<>();

    /**
     * @param scheduler   quartz调度器
     * @param startAtTime 任务执行时刻
     * @param name        任务名称
     * @param group       任务组名称
     * @param jobBean     具体任务
     */
    public static void createJobByStartAt(Scheduler scheduler, long startAtTime, String name, String group, Class<? extends Job> jobBean) {
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).build();
        createJob(scheduler, name, group, trigger, jobBean);

    }

    /**
     * @param scheduler quartz调度器
     * @param name      任务名称
     * @param group     任务组名称
     * @param cron      cron表达式
     * @param jobBean   具体任务
     */
    public static void createJobByCron(Scheduler scheduler, String name, String group, String cron, Class<? extends Job> jobBean) {
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
        createJob(scheduler, name, group, trigger, jobBean);
    }

    @SneakyThrows
    private static void createJob(Scheduler scheduler, String name, String group, Trigger trigger, Class<? extends Job> jobBean) {
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(jobBean).withIdentity(name, group).build();
        try {
            // 将触发器与任务绑定到调度器内
            scheduler.scheduleJob(jobDetail, trigger);

            // 启动成功，如果是 Cron Job，记录到 jobDetailMap 中
            if (trigger instanceof CronTrigger) {
                jobDetailMap.put(jobBean, jobDetail);
            }
            log.info("启动 Job 成功，JobName：{}, Job：{}", jobDetail.getKey().getName(), jobBean);
        } catch (SchedulerException e) {
            log.error("启动 Job 失败，JobName：{}, Job：{}", jobDetail.getKey().getName(), jobBean, e);
            throw e;
        }
    }

    /**
     * 停止 Job
     *
     * @param jobBean
     */
    @SneakyThrows
    public static void stopJob(Scheduler scheduler, Class<? extends Job> jobBean) {
        JobDetail jobDetail = jobDetailMap.get(jobBean);
        if (jobDetail != null) {
            try {
                scheduler.deleteJob(jobDetail.getKey());
                jobDetailMap.remove(jobDetail.getKey());
                log.info("停止 Job 成功，JobName：{}, Job：{}", jobDetail.getKey().getName(), jobBean);
            } catch (SchedulerException e) {
                log.error("停止 Job 失败，JobName：{}, Job：{}", jobDetail.getKey().getName(), jobBean, e);
                throw e;
            }
        }
    }

}
