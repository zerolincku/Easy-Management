package com.linck.management.system.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.IdModel;
import com.linck.management.common.model.ListWithPage;
import com.linck.management.common.model.Page;
import com.linck.management.system.entity.SysRole;
import com.linck.management.system.service.SysPermissionService;
import com.linck.management.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-01 21:23
 **/
@Api(tags = "角色管理")
@RestController
@RequestMapping("sysRole")
public class SysRoleController {

    private static final Logger log = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysPermissionService sysPermissionService;

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

    @ApiOperation("查询角色权限id集合")
    @PostMapping("permissionIdList")
    public Result<List<Long>> permissionIdList(@RequestBody @Validated IdModel idModel) {
        // FIXME 完成这个方法和保存角色权限接口
        return null;
    }

    @ApiOperation("修改角色")
    @PostMapping("update")
    public Result update(@RequestBody SysRole sysRole) {
        sysRole.setCreateTime(null);
        sysRoleService.updateById(sysRole);
        return Result.success(null);
    }

    @ApiOperation("新增角色")
    @PostMapping("add")
    public Result add(@RequestBody SysRole sysRole) {
        sysRoleService.save(sysRole);
        return Result.success(null);
    }

    @ApiOperation("删除角色")
    @PostMapping("remove")
    public Result remove(@RequestBody @Validated IdModel idModel) {
        sysRoleService.removeById(idModel.getId());
        return Result.success(null);
    }

}
