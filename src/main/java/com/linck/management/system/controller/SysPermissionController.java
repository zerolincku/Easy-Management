package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linck.management.common.api.Result;
import com.linck.management.common.constant.StateEnum;
import com.linck.management.common.model.IdModel;
import com.linck.management.common.model.StateModel;
import com.linck.management.system.contants.SysPermissionTypeEnum;
import com.linck.management.system.entity.SysPermission;
import com.linck.management.system.model.dto.SysPermissionDTO;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-23 23:39
 **/
@Api(tags = "系统权限")
@RestController
@RequestMapping("sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;


    @ApiOperation("查询权限列表(嵌套封装)")
    @PostMapping("list")
    public Result<List<SysMenuAndButton>> list(@RequestBody StateModel stateModel) {
        List<SysMenuAndButton> result = sysPermissionService.allMenuAndButton(stateModel);
        return Result.success(result);
    }

    @ApiOperation("所有菜单和按钮")
    @PostMapping("allMenuAndButton")
    public Result<Map<String, List<SysPermission>>> allMenuAndButton() {
        List<SysPermission> list = sysPermissionService.list(new QueryWrapper<SysPermission>().ne("type", SysPermissionTypeEnum.PERMISSION.getType()).eq("state", StateEnum.ENABLE.getState()));
        List<SysPermission> menus = list.stream().filter(t -> t.getType().equals(SysPermissionTypeEnum.MENU.getType())).collect(Collectors.toList());
        List<SysPermission> buttons = list.stream().filter(t -> t.getType().equals(SysPermissionTypeEnum.BUTTON.getType())).collect(Collectors.toList());
        Map<String, List<SysPermission>> result = new HashMap<>();
        result.put("menus", menus);
        result.put("buttons", buttons);
        return Result.success(result);
    }

    @ApiOperation("新增权限")
    @PostMapping("add")
    public Result<Long> add(@RequestBody @Validated SysPermissionDTO permissionDTO) {
        permissionDTO.setId(null);
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(permissionDTO, sysPermission);
        sysPermission.setCreateTime(new Date());
        sysPermissionService.save(sysPermission);
        return Result.success(sysPermission.getId());
    }

    @ApiOperation("更新权限")
    @PostMapping("update")
    public Result<Long> update(@RequestBody @Validated SysPermissionDTO permissionDTO) {
        SysPermission sysPermission = new SysPermission();
        BeanUtils.copyProperties(permissionDTO, sysPermission);
        sysPermission.setUpdateTime(new Date());
        sysPermissionService.updateById(sysPermission);
        return Result.success(sysPermission.getId());
    }

    @ApiOperation("删除权限")
    @PostMapping("remove")
    public Result remove(@RequestBody IdModel idModel) {
        sysPermissionService.removeById(idModel.getId());
        sysPermissionService.remove(new QueryWrapper<SysPermission>().eq("pid", idModel.getId()));
        return Result.success(null);
    }
}
