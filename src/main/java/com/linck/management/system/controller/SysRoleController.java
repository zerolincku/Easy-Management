package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.IdModel;
import com.linck.management.common.model.ListWithPage;
import com.linck.management.common.model.Page;
import com.linck.management.system.entity.SysRole;
import com.linck.management.system.entity.SysRolePermission;
import com.linck.management.system.model.dto.RolePermissionDTO;
import com.linck.management.system.service.SysPermissionService;
import com.linck.management.system.service.SysRolePermissionService;
import com.linck.management.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-01 21:23
 **/
@Api(tags = "系统角色")
@RestController
@RequestMapping("sysRole")
public class SysRoleController {

    private static final Logger log = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @PreAuthorize("hasAuthority('role:view')")
    @ApiOperation("查询角色列表")
    @PostMapping("list")
    public Result<ListWithPage> list(@RequestBody(required = false) Page page) {
        // 如果没有分页参数，初始化参数
        page.ifNotPageSetDefault();
        List<SysRole> list = sysRoleService.selectByPage(page);
        PageInfo pageInfo = new PageInfo(list);
        ListWithPage<List> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
    }

    @PreAuthorize("hasAuthority('role:view')")
    @ApiOperation("查询角色权限id集合")
    @PostMapping("permissionIdList")
    public Result<List<Long>> permissionIdList(@RequestBody @Validated IdModel idModel) {
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionService.list(new QueryWrapper<SysRolePermission>().eq("r_id", idModel.getId()));
        List<Long> permissionIdList = sysRolePermissionList.stream().map(SysRolePermission::getpId).collect(Collectors.toList());
        return Result.success(permissionIdList);
    }

    @PreAuthorize("hasAuthority('role:update')")
    @ApiOperation("保存角色权限映射")
    @PostMapping("saveSolePermission")
    public Result saveSolePermission(@RequestBody @Validated RolePermissionDTO rolePermissionDTO) {
        return sysRoleService.saveSolePermission(rolePermissionDTO);
    }

    @PreAuthorize("hasAuthority('role:update')")
    @ApiOperation("修改角色")
    @PostMapping("update")
    public Result update(@RequestBody SysRole sysRole) {
        sysRole.setCreateTime(null);
        sysRoleService.updateById(sysRole);
        return Result.success("");
    }

    @PreAuthorize("hasAuthority('role:add')")
    @ApiOperation("新增角色")
    @PostMapping("add")
    public Result add(@RequestBody SysRole sysRole) {
        if (sysRole.getValue() == null) {
            return Result.failed("内容不能为空");
        }
        return sysRoleService.add(sysRole);
    }

    @PreAuthorize("hasAuthority('role:remove')")
    @ApiOperation("删除角色")
    @PostMapping("remove")
    public Result remove(@RequestBody @Validated IdModel idModel) {
        sysRoleService.removeById(idModel.getId());
        return Result.success("");
    }

}
