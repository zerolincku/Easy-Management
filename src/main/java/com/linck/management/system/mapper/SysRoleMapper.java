package com.linck.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.common.model.Page;
import com.linck.management.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author linck
 * @since 2020-08-09
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 分页查询角色
     *
     * @param page
     * @return
     */
    List<SysRole> list(Page page);
}
