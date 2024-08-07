package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.dto.IdsDto;
import com.linck.management.common.model.enums.StatusEnum;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.common.util.QueryCondition;
import com.linck.management.common.validate.Insert;
import com.linck.management.system.contants.SysPermissionTypeEnum;
import com.linck.management.system.model.entity.SysPermission;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author linck
 **/
@Api(tags = "系统权限")
@RestController
@RequestMapping("sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    @ApiOperation("查询列表")
    @GetMapping("page")
    public Result<ListWithPage<SysPermission>> page(QueryCondition<SysPermission> condition) {
        QueryWrapper<SysPermission> queryWrapper = condition.dealQueryCondition(SysPermission.class);
        Page<SysPermission> page = sysPermissionService.page(condition.page(), queryWrapper);
        return Result.success(new ListWithPage<>(page.getRecords(), page.getTotal()));
    }

    @ApiOperation("新增")
    @PostMapping
    public Result<String> add(@RequestBody @Validated(Insert.class) SysPermission sysPermission) {
        sysPermissionService.save(sysPermission);
        return Result.success();
    }

    @ApiOperation("修改")
    @PutMapping
    @CacheEvict(cacheNames = "permission", allEntries = true)
    public Result<String> update(@RequestBody SysPermission sysPermission) {
        sysPermissionService.updateById(sysPermission);
        return Result.success();
    }

    @ApiOperation("删除")
    @DeleteMapping
    @CacheEvict(cacheNames = "permission", allEntries = true)
    public Result<String> remove(@RequestBody @Validated IdsDto idsDto) {
        sysPermissionService.removeBatchByIds(idsDto.getIds());
        sysPermissionService.remove(new QueryWrapper<SysPermission>().in("pid", idsDto.getIds()));
        return Result.success();
    }

    @ApiOperation("查询系统用户")
    @GetMapping
    public Result<SysPermission> get(@RequestBody @Validated IdDto idDto) {
        return Result.success(sysPermissionService.getById(idDto.getId()));
    }

    @ApiOperation("查询权限列表(嵌套封装)")
    @PreAuthorize("hasAuthority('permission:view')")
    @GetMapping("getAllPermission")
    public Result<List<SysMenuAndButton>> getAllPermission() {
        List<SysMenuAndButton> result = sysPermissionService.allMenuAndButton();
        return Result.success(result);
    }

    @ApiOperation("所有菜单和按钮")
    @PreAuthorize("hasAuthority('permission:view')")
    @PostMapping("allMenuAndButton")
    public Result<Map<String, List<SysPermission>>> allMenuAndButton() {
        List<SysPermission> list = sysPermissionService.list(new QueryWrapper<SysPermission>().ne("type", SysPermissionTypeEnum.PERMISSION).eq("status", StatusEnum.ENABLE.getValue()));
        List<SysPermission> menus = list.stream().filter(t -> t.getType().equals(SysPermissionTypeEnum.MENU)).collect(Collectors.toList());
        List<SysPermission> buttons = list.stream().filter(t -> t.getType().equals(SysPermissionTypeEnum.BUTTON)).collect(Collectors.toList());
        Map<String, List<SysPermission>> result = new HashMap<>();
        result.put("menus", menus);
        result.put("buttons", buttons);
        return Result.success(result);
    }

}
