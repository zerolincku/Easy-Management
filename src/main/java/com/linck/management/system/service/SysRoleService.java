package com.linck.management.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.common.api.ResultCodeEnum;
import com.linck.management.common.exception.BizException;
import com.linck.management.common.model.dto.IdsDto;
import com.linck.management.common.model.dto.PageDto;
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
 * @create 2020-08-09
 */
@Service
public class SysRoleService extends ServiceImpl<SysRoleMapper, SysRole> {

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 分页查询
     */
    public List<SysRole> selectByPage(PageDto pageDto) {
        PageHelper.startPage(pageDto.getPageNum(), pageDto.getPageSize());
        return baseMapper.selectList(null);
    }

    /**
     * 新增
     */
    public SysRole insert(SysRole sysRole) {
        Long count = baseMapper.selectCount(new QueryWrapper<SysRole>().eq("value", sysRole.getValue()));
        if (count > 0) {
            throw new BizException(ResultCodeEnum.FAILED, "当前内容已经存在");
        }
        baseMapper.insert(sysRole);
        return sysRole;
    }

    /**
     * 批量新增
     */
    public int batchInsert(List<SysRole> list) {
        if (list.isEmpty()) {
            return 0;
        }
        return baseMapper.batchInsert(list);
    }

    /**
     * 删除
     */
    public int remove(IdsDto ids) {
        if (ids.getIds().isEmpty()) {
            return 0;
        }
        return baseMapper.deleteBatchIds(ids.getIds());
    }

    /**
     * 修改
     */
    public int update(SysRole sysRole) {
        return baseMapper.updateById(sysRole);
    }
    
    /**
     * 保存角色权限列表
     */
    public int saveSolePermission(RolePermissionDto rolePermissionDto) {
        List<SysRolePermission> databaseList = sysRolePermissionService.list(new QueryWrapper<SysRolePermission>().eq("r_id", rolePermissionDto.getRoleId()));
        List<Long> databasePermissionIdList = databaseList.stream().map(SysRolePermission::getPId).collect(Collectors.toList());
        // 查找待删除的映射
        List<Long> deleteIdList = databaseList.stream().filter(t -> !rolePermissionDto.getPermissionIdList().contains(t.getPId())).map(SysRolePermission::getId).collect(Collectors.toList());
        // 查找待添加的映射
        List<SysRolePermission> insertList = rolePermissionDto.getPermissionIdList().stream().filter(t -> !databasePermissionIdList.contains(t)).map(t -> new SysRolePermission(null, rolePermissionDto.getRoleId(), t)).collect(Collectors.toList());
        if (!deleteIdList.isEmpty()) {
            sysRolePermissionService.removeByIds(deleteIdList);
        }
        int insertCount = 0;
        if (!insertList.isEmpty()) {
            // FIXME 改用自定义 batchInsert
            sysRolePermissionService.saveBatch(insertList);
        }
        return insertCount;
    }
    
}
