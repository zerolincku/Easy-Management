package com.linck.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.system.model.entity.SysRolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linck
 * @create 2020-08-09
 */
@Repository
public interface SysRolePermissionMapper extends BaseMapper<SysRolePermission> {
    Integer insertList(List<SysRolePermission> list);
}
