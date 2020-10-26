package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.linck.management.common.api.CommonResult;
import com.linck.management.common.constant.StateEnum;
import com.linck.management.system.contants.SysPermissionTypeEnum;
import com.linck.management.system.entity.SysPermission;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.model.vo.SysPermissionVO;
import com.linck.management.system.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-10-23 23:39
 **/
@Api(tags = "系统权限")
@RestController
@RequestMapping("sysPermission")
public class SysPermissionController {

    @Autowired
    private SysPermissionService sysPermissionService;


    @ApiOperation("查询所有权限")
    @PostMapping("list")
    public CommonResult<List<SysMenuAndButton>> list() {
        List<SysPermission> sysPermissions = sysPermissionService.listAll();
        List<SysMenuAndButton> result = new ArrayList<>();
        sysPermissions.forEach(sysPermission -> {
            // 封住一级折叠菜单
            if (sysPermission.getType().equals(SysPermissionTypeEnum.MENU.getType())) {
                SysMenuAndButton menuAndButton = new SysMenuAndButton();
                BeanUtils.copyProperties(sysPermission, menuAndButton);
                result.add(menuAndButton);
                // 封装二级按钮
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.BUTTON.getType())) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                Optional<SysMenuAndButton> optional = result.stream().filter(t -> t.getId().equals(sysPermissionVO.getPid())).findFirst();
                if (optional.get().getChildrenList() == null) {
                    optional.get().setChildrenList(new ArrayList<>());
                }
                optional.get().getChildrenList().add(sysPermissionVO);
                // 封装权限
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.PERMISSION.getType())) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                Optional<SysPermission> button = sysPermissions.stream().filter(t -> t.getId().equals(sysPermissionVO.getPid())).findFirst();
                Optional<SysPermission> menu = sysPermissions.stream().filter(t -> t.getId().equals(button.get().getPid())).findFirst();
                Optional<SysMenuAndButton> resultMenu = result.stream().filter(t -> t.getId().equals(menu.get().getId())).findFirst();
                Optional<SysPermissionVO> resultButton = resultMenu.get().getChildrenList().stream().filter(t -> t.getId().equals(button.get().getId())).findFirst();
                if (resultButton.get().getChildrenList() == null) {
                    resultButton.get().setChildrenList(new ArrayList<>());
                }
                resultButton.get().getChildrenList().add(sysPermissionVO);
            }
        });
        return CommonResult.success(result);
    }

    @ApiOperation("所有一级菜单")
    @PostMapping("allMenu")
    public CommonResult<List<SysPermissionVO>> allMenu() {
        List<SysPermission> list = sysPermissionService.list(new QueryWrapper<SysPermission>().eq("type", SysPermissionTypeEnum.MENU.getType()).eq("state", StateEnum.ENABLE.getState()));
        // FIXME
        return null;
    }

    @ApiOperation("新增权限")
    @PostMapping("add")
    public CommonResult<Long> add(@RequestBody SysPermission sysPermission) {
        sysPermission.setCreateTime(new Date());
        sysPermissionService.save(sysPermission);
        return CommonResult.success(sysPermission.getId());
    }

    @ApiOperation("更新权限")
    @PostMapping("update")
    public CommonResult<Long> update(@RequestBody SysPermission sysPermission) {
        sysPermission.setUpdateTime(new Date());
        sysPermissionService.updateById(sysPermission);
        return CommonResult.success(sysPermission.getId());
    }

}
