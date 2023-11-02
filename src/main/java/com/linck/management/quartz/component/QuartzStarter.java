package com.linck.management.quartz.component;

import com.linck.management.common.model.enums.StatusEnum;
import com.linck.management.quartz.mapper.SysJobMapper;
import com.linck.management.quartz.model.entity.SysJob;
import com.linck.management.quartz.util.JobUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 遍历 SysJob 表下的 Job，依次启动所有是开启状态的 Job
 * @author linck
 **/
@Slf4j
@Component
@Order(2)
public class QuartzStarter implements ApplicationRunner {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private SysJobMapper sysJobMapper;

    @Override
    public void run(ApplicationArguments args) {
        if (log.isDebugEnabled()) {
            log.debug("QuartzConfig PostConstruct Init");
        }
        List<SysJob> sysJobList = sysJobMapper.selectList(null);
        sysJobList.forEach(sysJob -> {
            if (StatusEnum.ENABLE.equals(sysJob.getStatus())) {
                try {
                    JobUtils.createJobByCron(scheduler, sysJob);
                } catch (Exception e) {
                    log.error("启动 job 失败", e);
                }
            }
        });
    }
}
