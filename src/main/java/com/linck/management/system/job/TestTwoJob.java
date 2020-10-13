package com.linck.management.system.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: management
 * @description 测试定时任务
 * @author: Linck
 * @create: 2020-09-16 22:44
 **/
public class TestTwoJob implements Job{

    private static final Logger log = LoggerFactory.getLogger(TestTwoJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        log.debug("定时任务TestTwoJob执行 {}", sdf.format(new Date()));
    }
}
