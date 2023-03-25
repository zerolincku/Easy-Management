package com.linck.management.quartz.job;

import lombok.extern.slf4j.Slf4j;

/**
 * @author linck
 **/
@Slf4j
public class ExceptionJob extends AbstractJob {
    @Override
    public String run() {
        log.info("Test2Job 执行");
        int a = 1 / 0;
        return "成功";
    }
}
