package com.linck.management.quartz.job;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * @author linck
 **/
@Slf4j
public class RandomJob extends AbstractJob {

    /**
     * 模拟长时间运行和出错
     */
    @Override
    public String run() throws InterruptedException, NoSuchMethodException {
        log.info("Test3Job 执行");
        Random random = new Random();
        Thread.sleep(random.nextInt(5000));
        if (random.nextInt(10) > 5) {
            Method error = this.getClass().getDeclaredMethod("error");
            System.out.println(error);
        }
        return "成功";
    }
}
