package com.linck.management.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linck.management.common.model.Page;
import com.linck.management.system.entity.SysRole;
import com.linck.management.system.mapper.SysRoleMapper;
import com.linck.management.system.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linck
 * @since 2020-08-09
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 分页查询角色
     *
     * @param page
     * @return
     */
    @Override
    public List<SysRole> selectByPage(Page page) {
        // PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return roleMapper.selectList(null);
    }
}
