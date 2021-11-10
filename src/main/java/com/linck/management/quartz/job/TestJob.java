package com.linck.management.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-13 21:44
 **/
@Slf4j
public class TestJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("TestJob 执行");
    }
}
