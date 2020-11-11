package com.linck.management.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.common.model.Page;
import com.linck.management.system.entity.SysRole;
import com.linck.management.system.mapper.SysRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author linck
 * @since 2020-08-09
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

    @Autowired
    private SysRoleMapper roleMapper;

    /**
     * 分页查询角色
     *
     * @param page
     * @return
     */
    public List<SysRole> selectByPage(Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        return roleMapper.selectList(null);
    }
}
