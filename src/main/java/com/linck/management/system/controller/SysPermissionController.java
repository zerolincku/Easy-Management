package com.linck.management.system.controller;

import com.linck.management.system.entity.SysPermission;
import com.linck.management.system.service.SysPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<SysPermission> list() {
        // FIXME
        List<SysPermission> sysPermissions = sysPermissionService.listAll();
        return null;
    }

}
