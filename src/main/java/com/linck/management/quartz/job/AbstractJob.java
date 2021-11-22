package com.linck.management.quartz.job;

import com.linck.management.common.util.SpringContextHolder;
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

    private static Long jobId;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        SysJobLog sysJobLog = before(jobExecutionContext);
        try {
            sysJobLog.setMsg(run());
        } catch (Exception e) {
            sysJobLog.setResult(0);
            String msg = e.toString();
            if (msg.length() > 1024) {
                msg.substring(0, 1024);
            }
            sysJobLog.setMsg(msg);
            log.error("Job 运行出错 {}，", this.getClass(), e);
        }
        after(sysJobLog);
    }

    public SysJobLog before(JobExecutionContext jobExecutionContext) {
        log.info("before");
        SysJobLog sysJobLog = new SysJobLog();
        sysJobLog.setJobId(jobId);
        sysJobLog.setResult(1);
        sysJobLog.setSpendTime((int) System.currentTimeMillis());
        return sysJobLog;
    }

    public void after(SysJobLog sysJobLog) {
        log.info("after");
        sysJobLog.setSpendTime((int) (System.currentTimeMillis() - sysJobLog.getSpendTime()));
        sysJobLog.setCreateTime(LocalDateTime.now());
        SysJobLogMapper sysJobLogMapper = SpringContextHolder.getBean(SysJobLogMapper.class);
        sysJobLogMapper.insert(sysJobLog);
    }

    public static void setJobId(Long jobId) {
        AbstractJob.jobId = jobId;
    }

    public abstract String run() throws Exception;
}
