package com.linck.management.quartz.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.common.model.constant.StateEnum;
import com.linck.management.quartz.mapper.SysJobMapper;
import com.linck.management.quartz.model.dto.SysJobDTO;
import com.linck.management.quartz.model.entity.SysJob;
import com.linck.management.quartz.util.JobUtils;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linck
 * @create 2020-10-13
 */
@Service
public class SysJobService extends ServiceImpl<SysJobMapper, SysJob> {

    @Autowired
    private SysJobMapper sysJobMapper;

    @Autowired
    private Scheduler scheduler;

    /**
     * 条件检索，返回集合
     *
     * @param sysJobDTO
     */
    public List<SysJob> list(SysJobDTO sysJobDTO) {
        PageHelper.startPage(sysJobDTO.getPageNum(), sysJobDTO.getPageSize());
        return sysJobMapper.list(sysJobDTO);
    }

    public void start(Long id) {
        SysJob sysJob = sysJobMapper.selectById(id);
        JobUtils.createJobByCron(scheduler, sysJob);
        sysJob.setState(StateEnum.ENABLE.getState());
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
        sysJob.setState(StateEnum.DISABLE.getState());
        sysJobMapper.updateById(sysJob);
    }
}
