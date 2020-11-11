package com.linck.management.quartz.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.quartz.entity.SysJob;
import com.linck.management.quartz.mapper.SysJobMapper;
import com.linck.management.quartz.model.dto.SysJobDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linck
 * @since 2020-10-13
 */
@Service
public class SysJobService extends ServiceImpl<SysJobMapper, SysJob> {

    @Autowired
    private SysJobMapper sysJobMapper;

    /**
     * 条件检索，返回集合
     *
     * @param sysJobDTO
     */
    public List<SysJob> list(SysJobDTO sysJobDTO) {
        PageHelper.startPage(sysJobDTO.getPageNum(), sysJobDTO.getPageSize());
        return sysJobMapper.list(sysJobDTO);
    }
}
