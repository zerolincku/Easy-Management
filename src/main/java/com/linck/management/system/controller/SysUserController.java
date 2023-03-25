package com.linck.management.system.controller;

import com.github.pagehelper.PageInfo;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.SysUserDetails;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.system.model.dto.SysUserDto;
import com.linck.management.system.model.dto.SysUserSearchDto;
import com.linck.management.system.model.dto.UserRoleSaveModel;
import com.linck.management.system.model.entity.SysUser;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.model.vo.UserRoleModel;
import com.linck.management.system.service.SysPermissionService;
import com.linck.management.system.service.SysUserService;
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
 * 用户相关接口
 * @author linck
 **/
@Slf4j
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

    /**
     * 用户登录
     * @return 返回 token
     */
    @PostMapping("/login")
    public Result login(@RequestBody @Validated SysUserDto userDetail, HttpServletRequest request) {
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

    /**
     * 用户注册
     * @return 用户信息
     */
    @PostMapping("/register")
    public Result register(@RequestBody @Validated SysUserDto userDetail) {
        SysUser sysUser = sysUserService.register(userDetail);
        if (sysUser == null) {
            return Result.failed("该用户名已经被注册");
        }
        sysUser.setPwd(null);
        return Result.success(sysUser);
    }

    /**
     * 查询用户菜单和按钮
     * @return 权限树
     */
    @PreAuthorize("hasAuthority('user:view')")
    @PostMapping("/query/menu")
    public Result<List<SysMenuAndButton>> queryPermission() {
        // 从SecurityContextHolder获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        SysUser sysUser = sysUserDetails.getSysUser();
        List<SysMenuAndButton> result = sysPermissionService.getMenuAndButtonByUserId(sysUser.getId());
        return Result.success(result);
    }

    /**
     * 查询用户列表
     * @return 用户列表
     */
    @PreAuthorize("hasAuthority('user:view')")
    @PostMapping("list")
    public Result<ListWithPage<SysUser>> list(@RequestBody(required = false) SysUserSearchDto sysUserSearchDto) {
        List<SysUser> list = sysUserService.selectList(sysUserSearchDto);
        PageInfo<SysUser> pageInfo = new PageInfo<>(list);
        ListWithPage<SysUser> result = new ListWithPage<>();
        result.setList(list);
        result.setTotal(pageInfo.getTotal());
        return Result.success(result);
    }

    /**
     * 修改用户
     */
    @PreAuthorize("hasAuthority('user:update')")
    @PostMapping("update")
    public Result update(@RequestBody SysUser sysUser) {
        sysUser.setCreateTime(null);
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(sysUser.getPwd());
        sysUser.setPwd(encodePassword);
        sysUserService.updateById(sysUser);
        return Result.success("");
    }

    /**
     * 新增用户
     */
    @PreAuthorize("hasAuthority('user:add')")
    @PostMapping("add")
    public Result add(@RequestBody @Validated SysUser sysUser) {
        return sysUserService.add(sysUser);
    }

    /**
     * 删除用户
     * @param idDto id集合
     */
    @PreAuthorize("hasAuthority('user:remove')")
    @PostMapping("remove")
    public Result remove(@RequestBody @Validated IdDto idDto) {
        sysUserService.removeById(idDto.getId());
        return Result.success("");
    }

    /**
     * 查询用户角色列表
     */
    @PreAuthorize("hasAuthority('user:view')")
    @PostMapping("roleList")
    public Result<List<UserRoleModel>> roleList(@RequestBody @Validated IdDto model) {
        return sysUserService.roleList(model);
    }

    /**
     * 保存用户角色列表
     */
    @PreAuthorize("hasAuthority('user:update')")
    @PostMapping("saveRoleList")
    public Result saveRoleList(@RequestBody @Validated UserRoleSaveModel model) {
        return sysUserService.saveRoleList(model);
    }

}
