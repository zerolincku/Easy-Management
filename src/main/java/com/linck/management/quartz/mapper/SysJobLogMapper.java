package com.linck.management.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.quartz.model.dto.SysJobLogDTO;
import com.linck.management.quartz.model.entity.SysJobLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Mapper
 *
 * @author linck
 * @create 2021-11-15
 */
@Repository
public interface SysJobLogMapper extends BaseMapper<SysJobLog> {

    /**
     * 条件检索，返回集合
     */
    List<SysJobLog> list(SysJobLogDTO sysJobLogDTO);
}
