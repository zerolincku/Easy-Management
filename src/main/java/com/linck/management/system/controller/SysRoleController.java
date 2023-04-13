package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.dto.IdsDto;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.common.util.QueryCondition;
import com.linck.management.system.model.dto.RolePermissionDto;
import com.linck.management.system.model.entity.SysRole;
import com.linck.management.system.model.entity.SysRolePermission;
import com.linck.management.system.service.SysRolePermissionService;
import com.linck.management.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 系统角色
 *
 * @author linck
 **/
@Slf4j
@RestController
@RequestMapping("sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 查询角色列表
     */
    @PreAuthorize("hasAuthority('role:view')")
    @GetMapping("page")
    public Result<ListWithPage<SysRole>> list(QueryCondition<SysRole> condition) {
        QueryWrapper<SysRole> queryWrapper = condition.dealQueryCondition(SysRole.class);
        Page<SysRole> page = sysRoleService.page(condition.page(), queryWrapper);
        return Result.success(new ListWithPage<>(page.getRecords(), page.getTotal()));
    }

    /**
     * 新增角色
     */
    @PreAuthorize("hasAuthority('role:add')")
    @PostMapping
    public Result<SysRole> add(@RequestBody SysRole sysRole) {
        if (sysRole.getValue() == null) {
            return Result.failed("内容不能为空");
        }
        return Result.success(sysRoleService.insert(sysRole));
    }

    /**
     * 修改角色
     */
    @PreAuthorize("hasAuthority('role:update')")
    @PutMapping
    public Result<String> update(@RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return Result.success("");
    }

    /**
     * 删除角色
     */
    @PreAuthorize("hasAuthority('role:remove')")
    @DeleteMapping
    public Result<Integer> remove(@RequestBody @Validated IdsDto ids) {
        return Result.success(sysRoleService.remove(ids));
    }

    /**
     * 查询角色权限id集合
     */
    @PreAuthorize("hasAuthority('role:view')")
    @PostMapping("permissionIdList")
    public Result<List<Long>> permissionIdList(@RequestBody @Validated IdDto idDto) {
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionService.list(new QueryWrapper<SysRolePermission>().eq("r_id", idDto.getId()));
        List<Long> permissionIdList = sysRolePermissionList.stream().map(SysRolePermission::getPId).collect(Collectors.toList());
        return Result.success(permissionIdList);
    }

    /**
     * 保存角色权限映射
     */
    @PreAuthorize("hasAuthority('role:update')")
    @PostMapping("saveSolePermission")
    public Result<Integer> saveSolePermission(@RequestBody @Validated RolePermissionDto rolePermissionDto) {
        return Result.success(sysRoleService.saveSolePermission(rolePermissionDto));
    }

}
