package com.linck.management.quartz.job;

import lombok.extern.slf4j.Slf4j;

/**
 * @author linck
 **/
@Slf4j
public class SuccessJob extends AbstractJob {
    @Override
    public String run() {
        log.info("TestJob 执行");
        return "成功";
    }
}
