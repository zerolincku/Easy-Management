package com.linck.management.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.PageDTO;
import com.linck.management.system.mapper.SysRoleMapper;
import com.linck.management.system.mapper.SysRolePermissionMapper;
import com.linck.management.system.model.dto.RolePermissionDTO;
import com.linck.management.system.model.entity.SysRole;
import com.linck.management.system.model.entity.SysRolePermission;
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
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysRolePermissionMapper sysRolePermissionMapper;

    /**
     * 分页查询角色
     *
     * @param pageDTO
     * @return
     */
    public List<SysRole> selectByPage(PageDTO pageDTO) {
        PageHelper.startPage(pageDTO.getPageNum(), pageDTO.getPageSize());
        return sysRoleMapper.selectList(null);
    }

    /**
     * 保存角色权限列表
     *
     * @param rolePermissionDTO
     * @return
     */
    public Result saveSolePermission(RolePermissionDTO rolePermissionDTO) {
        List<SysRolePermission> databaseList = sysRolePermissionMapper.selectList(new QueryWrapper<SysRolePermission>().eq("r_id", rolePermissionDTO.getRoleId()));
        List<Long> databasePermissionIdList = databaseList.stream().map(t -> t.getPId()).collect(Collectors.toList());
        // 查找待删除的映射
        List<Long> deleteIdList = databaseList.stream().filter(t -> !rolePermissionDTO.getPermissionIdList().contains(t.getPId())).map(t -> t.getId()).collect(Collectors.toList());
        // 查找待添加的映射
        List<SysRolePermission> insertList = rolePermissionDTO.getPermissionIdList().stream().filter(t -> !databasePermissionIdList.contains(t)).map(t -> new SysRolePermission(null, rolePermissionDTO.getRoleId(), t)).collect(Collectors.toList());
        if (!deleteIdList.isEmpty()) {
            sysRolePermissionMapper.deleteBatchIds(deleteIdList);
        }
        Integer insertCount = 0;
        if (!insertList.isEmpty()) {
            insertCount = sysRolePermissionMapper.insertList(insertList);
        }
        return Result.success(insertCount);
    }

    /**
     * 新增角色
     *
     * @param sysRole
     * @return
     */
    public Result add(SysRole sysRole) {
        Integer count = sysRoleMapper.selectCount(new QueryWrapper<SysRole>().eq("value", sysRole.getValue()));
        if (count > 0) {
            return Result.failed("当前内容已经存在");
        }
        sysRoleMapper.insert(sysRole);
        return Result.success("");
    }
}
