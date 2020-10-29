package com.linck.management.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linck.management.quartz.entity.SysJob;
import com.linck.management.quartz.model.dto.SysJobDTO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author linck
 * @since 2020-10-13
 */
public interface SysJobService extends IService<SysJob> {
    /**
     * 条件检索，返回集合
     */
    public List<SysJob> list(SysJobDTO sysJobDTO);
}
