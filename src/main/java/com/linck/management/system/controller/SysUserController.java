package com.linck.management.system.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.SysUserDetails;
import com.linck.management.common.model.dto.IdDTO;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.system.model.dto.SysUserDTO;
import com.linck.management.system.model.dto.SysUserSearchDTO;
import com.linck.management.system.model.dto.UserRoleSaveModel;
import com.linck.management.system.model.entity.SysUser;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.model.vo.UserRoleModel;
import com.linck.management.system.service.SysPermissionService;
import com.linck.management.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@Slf4j
@Api(tags = "系统用户")
@RestController
@RequestMapping("/sys/user")
public class SysUserController {

    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysUserService sysUserService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @PreAuthorize("hasAuthority('user:view')")
    @ApiOperation("查询用户列表")
    @PostMapping("list")
    public Result<ListWithPage<SysUser>> list(@RequestBody(required = false) SysUserSearchDTO sysUserSearchDTO) {
        List<SysUser> list = sysUserService.selectList(sysUserSearchDTO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        ListWithPage<SysUser> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
    }

    @PreAuthorize("hasAuthority('user:update')")
    @ApiOperation("修改用户")
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        sysUser.setCreateTime(null);
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(sysUser.getPwd());
        sysUser.setPwd(encodePassword);
        sysUserService.updateById(sysUser);
        return Result.success("");
    }

    @PreAuthorize("hasAuthority('user:add')")
    @ApiOperation("新增用户")
    @PostMapping("add")
    public Result add(@RequestBody @Validated SysUser sysUser) {
        return sysUserService.add(sysUser);
    }

    @PreAuthorize("hasAuthority('user:remove')")
    @ApiOperation("删除用户")
    @PostMapping("remove")
    public Result remove(@RequestBody @Validated IdDTO idDTO) {
        sysUserService.removeById(idDTO.getId());
        return Result.success("");
    }

    @PreAuthorize("hasAuthority('user:view')")
    @ApiOperation("查询用户角色列表")
    @PostMapping("roleList")
    public Result<List<UserRoleModel>> roleList(@RequestBody @Validated IdDTO model) {
        return sysUserService.roleList(model);
    }

    @PreAuthorize("hasAuthority('user:update')")
    @ApiOperation("保存用户角色列表")
    @PostMapping("saveRoleList")
    public Result saveRoleList(@RequestBody @Validated UserRoleSaveModel model) {
        return sysUserService.saveRoleList(model);
    }

}
