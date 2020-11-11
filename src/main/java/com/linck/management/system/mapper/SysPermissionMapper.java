package com.linck.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.system.entity.SysPermission;

import java.util.List;

/**
 * @author linck
 * @create 2020-08-09
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 根据UserId查询所有启用状态权限
     *
     * @param id
     * @return
     */
    List<SysPermission> listByUserId(Long id);
}
