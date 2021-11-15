package com.linck.management.quartz.job;

import lombok.extern.slf4j.Slf4j;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-13 21:44
 **/
@Slf4j
public class Test2Job extends AbstractJob {
    @Override
    public void run() {
        int a = 1 / 0;
        log.info("Test2Job 执行 {}");
    }
}
