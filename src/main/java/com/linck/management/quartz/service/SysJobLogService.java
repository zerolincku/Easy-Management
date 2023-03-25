package com.linck.management.quartz.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.quartz.mapper.SysJobLogMapper;
import com.linck.management.quartz.model.dto.SysJobLogDto;
import com.linck.management.quartz.model.entity.SysJobLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linck
 */
@Service
public class SysJobLogService extends ServiceImpl<SysJobLogMapper, SysJobLog> {

    @Autowired
    private SysJobLogMapper sysJobLogMapper;

    /**
     * 条件检索，返回集合
     */
    public List<SysJobLog> list(SysJobLogDto sysJobLogDto) {
        PageHelper.startPage(sysJobLogDto.getPageNum(), sysJobLogDto.getPageSize());
        return sysJobLogMapper.list(sysJobLogDto);
    }
}
