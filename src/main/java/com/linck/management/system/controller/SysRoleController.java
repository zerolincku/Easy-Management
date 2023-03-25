package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.dto.PageDto;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.system.model.dto.RolePermissionDto;
import com.linck.management.system.model.entity.SysRole;
import com.linck.management.system.model.entity.SysRolePermission;
import com.linck.management.system.service.SysRolePermissionService;
import com.linck.management.system.service.SysRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("list")
    public Result<ListWithPage<SysRole>> list(@RequestBody(required = false) PageDto pageDto) {
        List<SysRole> list = sysRoleService.selectByPage(pageDto);
        PageInfo<SysRole> pageInfo = new PageInfo<>(list);
        ListWithPage<SysRole> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
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
    public Result saveSolePermission(@RequestBody @Validated RolePermissionDto rolePermissionDto) {
        return sysRoleService.saveSolePermission(rolePermissionDto);
    }

    /**
     * 修改角色
     */
    @PreAuthorize("hasAuthority('role:update')")
    @PostMapping("update")
    public Result update(@RequestBody SysRole sysRole) {
        sysRole.setCreateTime(null);
        sysRoleService.updateById(sysRole);
        return Result.success("");
    }

    /**
     * 新增角色
     */
    @PreAuthorize("hasAuthority('role:add')")
    @PostMapping("add")
    public Result add(@RequestBody SysRole sysRole) {
        if (sysRole.getValue() == null) {
            return Result.failed("内容不能为空");
        }
        return sysRoleService.add(sysRole);
    }

    /**
     * 删除角色
     */
    @PreAuthorize("hasAuthority('role:remove')")
    @PostMapping("remove")
    public Result remove(@RequestBody @Validated IdDto idDto) {
        sysRoleService.removeById(idDto.getId());
        return Result.success("");
    }

}
