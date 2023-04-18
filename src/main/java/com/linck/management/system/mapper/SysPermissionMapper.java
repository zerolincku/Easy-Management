package com.linck.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.system.model.entity.SysPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linck
 * @date 2020-08-09
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 根据UserId查询所有启用状态权限
     *
     * @param id 用户id
     */
    List<SysPermission> listByUserId(Long id);
}
