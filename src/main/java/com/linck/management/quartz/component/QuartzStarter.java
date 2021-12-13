package com.linck.management.quartz.component;

import com.linck.management.common.model.constant.StateEnum;
import com.linck.management.quartz.mapper.SysJobMapper;
import com.linck.management.quartz.model.entity.SysJob;
import com.linck.management.quartz.util.JobUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author: linck
 * @create: 2020-09-17 21:50
 * @description 定时任务启动器
 * <p>
 * 遍历 SysJob 表下的 Job，依次启动所有是开启状态的 Job
 **/
@Slf4j
@Component
public class QuartzStarter {

    @Autowired
    private Scheduler scheduler;
    @Autowired
    private SysJobMapper sysJobMapper;

    @PostConstruct
    public void init() {
        if (log.isDebugEnabled()) {
            log.debug("QuartzConfig PostConstruct Init");
        }
        List<SysJob> sysJobList = sysJobMapper.list(null);
        sysJobList.forEach(sysJob -> {
            if (StateEnum.ENABLE.equals(sysJob.getState())) {
                try {
                    JobUtils.createJobByCron(scheduler, sysJob);
                } catch (Exception e) {
                    log.error("启动 job 失败", e);
                }
            }
        });
    }

}
