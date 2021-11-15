package com.linck.management.quartz.job;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-13 21:44
 **/
@Slf4j
public class TestJob extends AbstractJob {
    @Override
    public void run() {
        log.info("TestJob 执行 {}");
    }
}
