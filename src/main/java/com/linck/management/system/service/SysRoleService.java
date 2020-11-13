package com.linck.management.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.Page;
import com.linck.management.system.entity.SysRole;
import com.linck.management.system.entity.SysRolePermission;
import com.linck.management.system.mapper.SysRoleMapper;
import com.linck.management.system.mapper.SysRolePermissionMapper;
import com.linck.management.system.model.dto.RolePermissionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linck
 * @create 2020-08-09
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

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

    public Result saveSolePermission(RolePermissionDTO rolePermissionDTO) {
        List<SysRolePermission> databaseList = sysRolePermissionMapper.selectList(new QueryWrapper<SysRolePermission>().eq("r_id", rolePermissionDTO.getRoleId()));
        List<Long> databasePermissionIdList = databaseList.stream().map(t -> t.getpId()).collect(Collectors.toList());
        // 查找待删除的映射
        List<Long> deleteIdList = databaseList.stream().filter(t -> !rolePermissionDTO.getPermissionIdList().contains(t.getpId())).map(t -> t.getId()).collect(Collectors.toList());
        // 查找待添加的映射
        List<SysRolePermission> insertList = rolePermissionDTO.getPermissionIdList().stream().filter(t -> !databasePermissionIdList.contains(t)).map(t -> new SysRolePermission(rolePermissionDTO.getRoleId(), t)).collect(Collectors.toList());
        if (!deleteIdList.isEmpty()) {
            sysRolePermissionMapper.deleteBatchIds(deleteIdList);
        }
        Integer insertCount = 0;
        if (!insertList.isEmpty()) {
            insertCount = sysRolePermissionMapper.insertList(insertList);
        }
        return Result.success(insertCount);
    }
}
