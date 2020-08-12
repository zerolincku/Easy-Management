package com.linck.management.system.service;

import com.linck.management.system.entity.SysPermission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author linck
 * @since 2020-08-09
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 根据UserId查询所有启用状态权限
     * @param id
     * @return
     */
    List<SysPermission> listByUserId(Long id);

}
