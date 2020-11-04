package com.linck.management.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.linck.management.common.model.Page;
import com.linck.management.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author linck
 * @since 2020-08-09
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 分页查询角色
     *
     * @param page
     * @return
     */
    List<SysRole> selectByPage(Page page);
}
