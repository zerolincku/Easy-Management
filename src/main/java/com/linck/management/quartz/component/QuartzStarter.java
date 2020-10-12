package com.linck.management.quartz.component;

import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @program: management
 * @description 定时任务启动器
 * @author: Linck
 * @create: 2020-09-17 21:50
 **/
@Component
public class QuartzStarter {

    private static final Logger log = LoggerFactory.getLogger(QuartzStarter.class);

    @Autowired
    private Scheduler scheduler;


    @PostConstruct
    public void init(){
        log.debug("QuartzConfig PostConstruct Init");
        //JobUtils.createJobByCron(scheduler,"testJob","Test", "0 */1 * * * ?",TestJob.class);
    }

}
