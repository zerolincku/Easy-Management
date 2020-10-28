package com.linck.management.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linck.management.system.entity.SysPermission;
import com.linck.management.system.model.vo.SysMenuAndButton;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author linck
 * @since 2020-08-09
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 根据UserId查询所有启用状态权限
     *
     * @param id
     * @return
     */
    List<SysPermission> listByUserId(Long id);

    /**
     * 查询所有权限列表
     *
     * @return
     */
    List<SysPermission> listAll();

    /**
     * 查询所有菜单和按钮
     */
    List<SysMenuAndButton> allMenuAndButton();

    /**
     * 查询用户菜单和按钮
     */
    List<SysMenuAndButton> getMenuAndButtonByUserId(Long userId);

}
