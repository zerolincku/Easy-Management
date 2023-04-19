package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdsDto;
import com.linck.management.common.model.dto.StatusDto;
import com.linck.management.common.model.enums.StatusEnum;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.common.util.QueryCondition;
import com.linck.management.common.validate.Insert;
import com.linck.management.system.contants.SysPermissionTypeEnum;
import com.linck.management.system.model.entity.SysPermission;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统权限
 *
 * @author linck
 **/
@RestController
@RequestMapping("sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;

    /**
     * 查询列表
     */
    @GetMapping("page")
    public Result<ListWithPage<SysPermission>> page(QueryCondition<SysPermission> condition) {
        QueryWrapper<SysPermission> queryWrapper = condition.dealQueryCondition(SysPermission.class);
        Page<SysPermission> page = sysPermissionService.page(condition.page(), queryWrapper);
        return Result.success(new ListWithPage<>(page.getRecords(), page.getTotal()));
    }

    /**
     * 新增
     */
    @PostMapping
    public Result<String> add(@RequestBody @Validated(Insert.class) SysPermission sysPermission) {
        sysPermissionService.save(sysPermission);
        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<String> update(@RequestBody SysPermission sysPermission) {
        sysPermissionService.updateById(sysPermission);
        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result<String> remove(@RequestBody @Validated IdsDto idsDto) {
        sysPermissionService.removeBatchByIds(idsDto.getIds());
        sysPermissionService.remove(new QueryWrapper<SysPermission>().in("pid", idsDto.getIds()));
        return Result.success();
    }

    /**
     * 查询权限列表(嵌套封装)
     */
    @PreAuthorize("hasAuthority('permission:view')")
    @PostMapping("list")
    public Result<List<SysMenuAndButton>> list(@RequestBody StatusDto statusDto) {
        List<SysMenuAndButton> result = sysPermissionService.allMenuAndButton(statusDto);
        return Result.success(result);
    }

    /**
     * 所有菜单和按钮
     */
    @PreAuthorize("hasAuthority('permission:view')")
    @PostMapping("allMenuAndButton")
    public Result<Map<String, List<SysPermission>>> allMenuAndButton() {
        List<SysPermission> list = sysPermissionService.list(new QueryWrapper<SysPermission>().ne("type", SysPermissionTypeEnum.PERMISSION.getType()).eq("status", StatusEnum.ENABLE.getValue()));
        List<SysPermission> menus = list.stream().filter(t -> t.getType().equals(SysPermissionTypeEnum.MENU.getType())).collect(Collectors.toList());
        List<SysPermission> buttons = list.stream().filter(t -> t.getType().equals(SysPermissionTypeEnum.BUTTON.getType())).collect(Collectors.toList());
        Map<String, List<SysPermission>> result = new HashMap<>();
        result.put("menus", menus);
        result.put("buttons", buttons);
        return Result.success(result);
    }

}
