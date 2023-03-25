package com.linck.management.quartz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.quartz.model.dto.SysJobLogDto;
import com.linck.management.quartz.model.entity.SysJobLog;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linck
 * @create 2021-11-15
 */
@Repository
public interface SysJobLogMapper extends BaseMapper<SysJobLog> {

    /**
     * 条件检索，返回集合
     */
    List<SysJobLog> list(SysJobLogDto sysJobLogDto);
}
