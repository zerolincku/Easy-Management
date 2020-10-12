package com.linck.management.quartz.util;

import org.quartz.*;

import java.util.Date;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-09-16 23:18
 **/
public class JobUtils {
    /**
     *
     * @param scheduler quartz调度器
     * @param startAtTime 任务执行时刻
     * @param name 任务名称
     * @param group 任务组名称
     * @param jobBean 具体任务
     */
    public static void createJobByStartAt(Scheduler scheduler, long startAtTime, String name, String group, Class jobBean){
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).startAt(new Date(startAtTime)).build();
        createJob(scheduler, name, group, trigger,jobBean);



    }

    /**
     *
     * @param scheduler quartz调度器
     * @param name 任务名称
     * @param group 任务组名称
     * @param cron cron表达式
     * @param jobBean 具体任务
     */
    public static void createJobByCron(Scheduler scheduler, String name, String group,String cron,Class jobBean){
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name,group).withSchedule(scheduleBuilder).build();
        createJob(scheduler,name,group,trigger,jobBean);
    }


    private static void createJob(Scheduler scheduler, String name, String group, Trigger trigger,Class jobBean) {
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(jobBean).withIdentity(name,group).build();
        try {
            //将触发器与任务绑定到调度器内
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
