package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.dto.StatusDto;
import com.linck.management.common.model.enums.StatusEnum;
import com.linck.management.system.contants.SysPermissionTypeEnum;
import com.linck.management.system.model.dto.SysPermissionDto;
import com.linck.management.system.model.entity.SysPermission;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.service.SysPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
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

    /**
     * 新增权限
     */
    @PreAuthorize("hasAuthority('permission:add')")
    @PostMapping("add")
    public Result add(@RequestBody @Validated SysPermissionDto permissionDto) {
        permissionDto.setId(null);
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(permissionDto, sysPermission);
        sysPermission.setCreateAt(LocalDateTime.now());
        return sysPermissionService.add(sysPermission);
    }

    /**
     * 更新权限
     */
    @PreAuthorize("hasAuthority('permission:update')")
    @PostMapping("update")
    public Result<Long> update(@RequestBody @Validated SysPermissionDto permissionDto) {
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(permissionDto, sysPermission);
        sysPermission.setUpdateAt(LocalDateTime.now());
        sysPermissionService.updateById(sysPermission);
        return Result.success(sysPermission.getId());
    }

    /**
     * 删除权限
     */
    @PreAuthorize("hasAuthority('permission:remove')")
    @PostMapping("remove")
    public Result<String> remove(@RequestBody @Validated IdDto idDto) {
        sysPermissionService.removeById(idDto.getId());
        sysPermissionService.remove(new QueryWrapper<SysPermission>().eq("pid", idDto.getId()));
        return Result.success("");
    }
}
