package com.linck.management.quartz.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.quartz.mapper.SysJobLogMapper;
import com.linck.management.quartz.model.dto.SysJobLogDTO;
import com.linck.management.quartz.model.entity.SysJobLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service
 *
 * @author linck
 * @create 2021-11-15
 */
@Service
public class SysJobLogService extends ServiceImpl<SysJobLogMapper, SysJobLog> {

    @Autowired
    private SysJobLogMapper sysJobLogMapper;

    /**
     * 条件检索，返回集合
     *
     * @param sysJobLogDTO
     */
    public List<SysJobLog> list(SysJobLogDTO sysJobLogDTO) {
        PageHelper.startPage(sysJobLogDTO.getPageNum(), sysJobLogDTO.getPageSize());
        return sysJobLogMapper.list(sysJobLogDTO);
    }
}
