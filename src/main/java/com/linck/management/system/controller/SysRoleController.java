package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.api.Result;
import com.linck.management.common.exception.BizException;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.dto.IdsDto;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.common.util.QueryCondition;
import com.linck.management.common.validate.Insert;
import com.linck.management.system.model.dto.RolePermissionDto;
import com.linck.management.system.model.entity.SysRole;
import com.linck.management.system.model.entity.SysRolePermission;
import com.linck.management.system.service.SysRolePermissionService;
import com.linck.management.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linck
 **/
@Api(tags = "系统角色")
@Slf4j
@RestController
@RequestMapping("sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysRolePermissionService sysRolePermissionService;

    @ApiOperation("查询系统角色列表")
    @GetMapping("page")
    public Result<ListWithPage<SysRole>> page(QueryCondition<SysRole> condition) {
        QueryWrapper<SysRole> queryWrapper = condition.dealQueryCondition(SysRole.class);
        Page<SysRole> page = sysRoleService.page(condition.page(), queryWrapper);
        return Result.success(new ListWithPage<>(page.getRecords(), page.getTotal()));
    }

    @ApiOperation("新增系统角色")
    @PostMapping
    public Result<String> add(@RequestBody @Validated(Insert.class) SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.success();
    }

    @ApiOperation("修改系统角色")
    @PutMapping
    public Result<String> update(@RequestBody SysRole sysRole) {
        sysRoleService.updateById(sysRole);
        return Result.success();
    }

    @ApiOperation("删除系统角色")
    @DeleteMapping
    public Result<String> remove(@RequestBody @Validated IdsDto idsDto) {
        if (idsDto.getIds().contains(1L)) {
            throw new BizException("系统管理员不允许删除");
        }
        sysRoleService.removeBatchByIds(idsDto.getIds());
        return Result.success();
    }

    @ApiOperation("查询系统用户")
    @GetMapping
    public Result<SysRole> get(@RequestBody @Validated IdDto idDto) {
        return Result.success(sysRoleService.getById(idDto.getId()));
    }

    @ApiOperation("查询角色权限id集合")
    @PreAuthorize("hasAuthority('role:view')")
    @PostMapping("permissionIdList")
    public Result<List<Long>> permissionIdList(@RequestBody @Validated IdDto idDto) {
        List<SysRolePermission> sysRolePermissionList = sysRolePermissionService.list(new QueryWrapper<SysRolePermission>().eq("r_id", idDto.getId()));
        List<Long> permissionIdList = sysRolePermissionList.stream().map(SysRolePermission::getPId).collect(Collectors.toList());
        return Result.success(permissionIdList);
    }

    @ApiOperation("保存角色权限映射")
    @PreAuthorize("hasAuthority('role:update')")
    @PostMapping("saveSolePermission")
    public Result<String> saveSolePermission(@RequestBody @Validated RolePermissionDto rolePermissionDto) {
        sysRoleService.saveSolePermission(rolePermissionDto);
        return Result.success();
    }

}
