package com.linck.management.quartz.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.common.model.enums.StatusEnum;
import com.linck.management.quartz.mapper.SysJobMapper;
import com.linck.management.quartz.model.dto.SysJobDto;
import com.linck.management.quartz.model.entity.SysJob;
import com.linck.management.quartz.util.JobUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linck
 */
@Service
public class SysJobService extends ServiceImpl<SysJobMapper, SysJob> {

    @Autowired
    private SysJobMapper sysJobMapper;

    @Autowired
    private Scheduler scheduler;

    /**
     * 条件检索，返回集合
     */
    public List<SysJob> list(SysJobDto sysJobDto) {
        PageHelper.startPage(sysJobDto.getPageNum(), sysJobDto.getPageSize());
        return sysJobMapper.list(sysJobDto);
    }

    public void start(Long id) {
        SysJob sysJob = sysJobMapper.selectById(id);
        JobUtils.createJobByCron(scheduler, sysJob);
        sysJob.setStatus(StatusEnum.ENABLE);
        sysJobMapper.updateById(sysJob);
    }

    public void stop(Long id) {
        SysJob sysJob = sysJobMapper.selectById(id);
        Class jobClass = null;
        try {
            jobClass = Class.forName(sysJob.getJobClass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        JobUtils.stopJob(scheduler, jobClass);
        sysJob.setStatus(StatusEnum.DISABLE);
        sysJobMapper.updateById(sysJob);
    }
}
