package com.linck.management.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.quartz.entity.SysJob;
import com.linck.management.quartz.mapper.SysJobMapper;
import com.linck.management.quartz.model.dto.SysJobDTO;
import com.linck.management.quartz.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linck
 * @since 2020-10-13
 */
@Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements SysJobService {

    @Autowired
    private SysJobMapper sysJobMapper;

    /**
     * 条件检索，返回集合
     *
     * @param sysJobDTO
     */
    @Override
    public List<SysJob> list(SysJobDTO sysJobDTO) {
        PageHelper.startPage(sysJobDTO.getPageNum(), sysJobDTO.getPageSize());
        return sysJobMapper.list(sysJobDTO);
    }
}
