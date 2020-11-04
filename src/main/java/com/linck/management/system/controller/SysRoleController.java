package com.linck.management.system.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.CommonResult;
import com.linck.management.common.model.ListWithPage;
import com.linck.management.common.model.Page;
import com.linck.management.system.entity.SysRole;
import com.linck.management.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-11-01 21:23
 **/
@Api(tags = "角色管理")
@RestController
@RequestMapping("sysRole")
public class SysRoleController {

    private static final Logger log = LoggerFactory.getLogger(SysRoleController.class);

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation("查询所有用户")
    @PostMapping("list")
    public CommonResult<ListWithPage> list(@RequestBody(required = false) Page page) {
        // 如果没有分页参数，初始化参数
        page.ifNotPageSetDefault();
        List<SysRole> list = sysRoleService.selectByPage(page);
        PageInfo pageInfo = new PageInfo(list);
        ListWithPage<List> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return CommonResult.success(result);
    }
}
