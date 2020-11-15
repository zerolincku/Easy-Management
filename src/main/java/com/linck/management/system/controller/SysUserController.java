package com.linck.management.system.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.IdModel;
import com.linck.management.common.model.ListWithPage;
import com.linck.management.common.model.SysUserDetails;
import com.linck.management.system.entity.SysUser;
import com.linck.management.system.model.dto.SysUserDTO;
import com.linck.management.system.model.dto.SysUserSearchDTO;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.service.SysPermissionService;
import com.linck.management.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: MyManagement
 * @description
 * @author: linck
 * @create: 2020-08-09 17:43
 **/
@Api(tags = "系统用户")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysUserService sysUserService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result login(@RequestBody @Validated SysUserDTO userDetail, HttpServletRequest request) {
        /*if(!userDetail.getVerificationCode().equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
            return CommonResult.validateFailed("验证码错误");
        }*/
        String token = sysUserService.login(userDetail);
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", tokenHead + " " + token);
        return Result.success(tokenMap);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public Result register(@RequestBody @Validated SysUserDTO userDetail) {
        SysUser sysUser = sysUserService.register(userDetail);
        if (sysUser == null) {
            return Result.failed("该用户名已经被注册");
        }
        sysUser.setPwd(null);
        return Result.success(sysUser);
    }

    @PreAuthorize("hasAuthority('user:view')")
    @ApiOperation("查询用户菜单和按钮")
    @PostMapping("/query/menu")
    public Result<List<SysMenuAndButton>> queryPermission() {
        // 从SecurityContextHolder获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        SysUser sysUser = sysUserDetails.getSysUser();
        List<SysMenuAndButton> result = sysPermissionService.getMenuAndButtonByUserId(sysUser.getId());
        return Result.success(result);
    }

    // FIXME 这里和下面没写
    @PreAuthorize("hasAuthority('role:view')")
    @ApiOperation("查询用户列表")
    @PostMapping("list")
    public Result<ListWithPage> list(@RequestBody(required = false) SysUserSearchDTO sysUserSearchDTO) {
        // 如果没有分页参数，初始化参数
        sysUserSearchDTO.ifNotPageSetDefault();
        List<SysUser> list = sysUserService.selectList(sysUserSearchDTO);
        PageInfo pageInfo = new PageInfo(list);
        ListWithPage<List> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
    }

    @PreAuthorize("hasAuthority('role:update')")
    @ApiOperation("修改用户")
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        sysUser.setCreateTime(null);
        sysUserService.updateById(sysUser);
        return Result.success("");
    }

    @PreAuthorize("hasAuthority('role:add')")
    @ApiOperation("新增用户")
    @PostMapping("add")
    public Result add(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return Result.success(sysUser.getId());
    }

    @PreAuthorize("hasAuthority('role:remove')")
    @ApiOperation("删除用户")
    @PostMapping("remove")
    public Result remove(@RequestBody @Validated IdModel idModel) {
        sysUserService.removeById(idModel.getId());
        return Result.success("");
    }

}
