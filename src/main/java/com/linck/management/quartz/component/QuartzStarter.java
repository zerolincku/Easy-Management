package com.linck.management.quartz.component;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: management
 * @description 定时任务启动器
 * @author: linck
 * @create: 2020-09-17 21:50
 **/
@Slf4j
@Component
public class QuartzStarter {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void init() {
        log.info("QuartzConfig PostConstruct Init");
        // JobUtils.createJobByCron(scheduler, "TestJob", "Test", "0 */1 * * * ?", TestJob.class);
    }

}
