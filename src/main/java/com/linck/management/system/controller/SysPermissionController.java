package com.linck.management.system.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
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
    public List<SysMenuAndButton> list() {
        // FIXME
        List<SysPermission> sysPermissions = sysPermissionService.listAll();
        List<SysMenuAndButton> result = new ArrayList<>();
        sysPermissions.forEach(sysPermission -> {
            // 封住一级折叠菜单
            if (sysPermission.getType().equals(SysPermissionTypeEnum.MENU.getType())) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                SysMenuAndButton menuAndButton = new SysMenuAndButton();
                menuAndButton.setMenu(sysPermissionVO);
                result.add(menuAndButton);
                // 封装二级按钮
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.BUTTON.getType())) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                Optional<SysMenuAndButton> optional = result.stream().filter(t -> t.getMenu().getId().equals(sysPermissionVO.getPid())).findFirst();
                if (optional.get().getButtons() == null) {
                    optional.get().setButtons(new ArrayList<>());
                }
                optional.get().getButtons().add(sysPermissionVO);
                // 封装权限
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.PERMISSION.getType())) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                Optional<SysPermission> button = sysPermissions.stream().filter(t -> t.getId().equals(sysPermissionVO.getPid())).findFirst();
                Optional<SysPermission> menu = sysPermissions.stream().filter(t -> t.getId().equals(button.get().getPid())).findFirst();
                Optional<SysMenuAndButton> resultMenu = result.stream().filter(t -> t.getMenu().getId().equals(menu.get().getId())).findFirst();
                Optional<SysPermissionVO> resultButton = resultMenu.get().getButtons().stream().filter(t -> t.getId().equals(button.get().getId())).findFirst();
                if (resultButton.get().getPermissions() == null) {
                    resultButton.get().setPermissions(new ArrayList<>());
                }
                resultButton.get().getPermissions().add(sysPermissionVO);
            }
        });
        return result;
    }
}
