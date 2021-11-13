package com.linck.management.quartz.util;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author: linck
 * @create: 2021-11-13
 */
@Slf4j
public abstract class AbstractJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        before(jobExecutionContext);
        try {
            run();
        } catch (Exception e) {
            log.error("Job 运行出错 {}，", this.getClass(), e);
        }
        after();
    }

    public void before(JobExecutionContext jobExecutionContext) {
        log.info("before");
    }

    public void after() {
        log.info("after");
    }

    public abstract void run();
}
