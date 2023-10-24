package com.linck.management.quartz.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linck.management.quartz.mapper.SysJobLogMapper;
import com.linck.management.quartz.model.entity.SysJobLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author linck
 */
@Service
public class SysJobLogService extends ServiceImpl<SysJobLogMapper, SysJobLog> {

    @Autowired
    private SysJobLogMapper sysJobLogMapper;

}
