package com.linck.management.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.quartz.entity.SysJob;
import com.linck.management.quartz.model.dto.SysJobDTO;

import java.util.List;

/**
 * @author linck
 * @create 2020-10-13
 */
public interface SysJobMapper extends BaseMapper<SysJob> {

    /**
     * 条件检索，返回集合
     */
    List<SysJob> list(SysJobDTO sysJobDTO);
}
