package com.linck.management.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linck.management.common.exception.BizException;
import com.linck.management.system.mapper.SysRoleMapper;
import com.linck.management.system.model.dto.RolePermissionDto;
import com.linck.management.system.model.entity.SysRole;
import com.linck.management.system.model.entity.SysRolePermission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linck
 * @date 2020-08-09
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 新增
     */
    @Override
    public boolean save(SysRole sysRole) {
        Long count = baseMapper.selectCount(new QueryWrapper<SysRole>().eq("value", sysRole.getValue()));
        if (count > 0) {
            throw new BizException("当前内容已经存在");
        }
        baseMapper.insert(sysRole);
        return true;
    }
    
    /**
     * 保存角色权限列表
     */
    public void saveSolePermission(RolePermissionDto rolePermissionDto) {
        List<SysRolePermission> databaseList = sysRolePermissionService.list(new QueryWrapper<SysRolePermission>().eq("r_id", rolePermissionDto.getRoleId()));
        List<Long> databasePermissionIdList = databaseList.stream().map(SysRolePermission::getPId).collect(Collectors.toList());
        // 查找待删除的映射
        List<Long> deleteIdList = databaseList.stream().filter(t -> !rolePermissionDto.getPermissionIdList().contains(t.getPId())).map(SysRolePermission::getId).collect(Collectors.toList());
        // 查找待添加的映射
        List<SysRolePermission> insertList = rolePermissionDto.getPermissionIdList().stream().filter(t -> !databasePermissionIdList.contains(t)).map(t -> new SysRolePermission(null, rolePermissionDto.getRoleId(), t)).collect(Collectors.toList());
        if (!deleteIdList.isEmpty()) {
            sysRolePermissionService.removeByIds(deleteIdList);
        }
        if (!insertList.isEmpty()) {
            sysRolePermissionService.saveBatch(insertList, 100);
        }
    }
    
}
