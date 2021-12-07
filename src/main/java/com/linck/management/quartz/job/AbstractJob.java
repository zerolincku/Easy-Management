package com.linck.management.quartz.job;

import com.linck.management.common.util.SpringContextHolder;
import com.linck.management.quartz.component.JobInitialize;
import com.linck.management.quartz.mapper.SysJobLogMapper;
import com.linck.management.quartz.model.entity.SysJobLog;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;

/**
 * @author: linck
 * @create: 2021-11-13
 */
@Slf4j
public abstract class AbstractJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SysJobLog sysJobLog = before(jobExecutionContext);
        try {
            sysJobLog.setMsg(run());
        } catch (Exception e) {
            sysJobLog.setResult(SysJobLog.ResultEnum.FAILURE);
            String msg = e.toString();
            if (msg.length() > 1024) {
                msg.substring(0, 1024);
            }
            sysJobLog.setMsg(msg);
            log.error("Job 运行出错 {}，", this.getClass().getName(), e);
        }
        after(sysJobLog);
    }

    public SysJobLog before(JobExecutionContext jobExecutionContext) {
        if (log.isDebugEnabled()) {
            log.debug("{} before method execute", this.getClass().getName());
        }
        SysJobLog sysJobLog = new SysJobLog();
        sysJobLog.setJobId(JobInitialize.getJobId(this.getClass()));
        sysJobLog.setResult(SysJobLog.ResultEnum.SUCCESS);
        sysJobLog.setSpendTime((int) System.currentTimeMillis());
        return sysJobLog;
    }

    public void after(SysJobLog sysJobLog) {
        if (log.isDebugEnabled()) {
            log.debug("{} after method execute", this.getClass().getName());
        }
        sysJobLog.setSpendTime((int) (System.currentTimeMillis() - sysJobLog.getSpendTime()));
        sysJobLog.setCreateTime(LocalDateTime.now());
        SysJobLogMapper sysJobLogMapper = SpringContextHolder.getBean(SysJobLogMapper.class);
        sysJobLogMapper.insert(sysJobLog);
    }

    public abstract String run() throws Exception;
}
