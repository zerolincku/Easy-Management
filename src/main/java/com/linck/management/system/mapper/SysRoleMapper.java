package com.linck.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.system.model.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linck
 * @create 2020-08-09
 */
@Repository
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 批量新增
     */
    int batchInsert(List<SysRole> list);
}
