package com.linck.management.quartz.job;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-13 21:44
 **/
@Slf4j
public class Test3Job extends AbstractJob {

    /**
     * 模拟长时间运行和出错
     *
     * @return
     * @throws InterruptedException
     * @throws NoSuchMethodException
     */
    @Override
    public String run() throws InterruptedException, NoSuchMethodException {
        log.info("Test3Job 执行");
        Random random = new Random();
        Thread.sleep(random.nextInt(5000));
        if (random.nextInt(10) > 5) {
            this.getClass().getDeclaredMethod("error");
        }
        return "成功";
    }
}
