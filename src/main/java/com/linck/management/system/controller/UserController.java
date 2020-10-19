package com.linck.management.system.controller;

import com.linck.management.common.api.CommonResult;
import com.linck.management.system.contants.SysPermissionTypeEnum;
import com.linck.management.system.dto.LoginUserDTO;
import com.linck.management.system.dto.SysUserDetails;
import com.linck.management.system.entity.SysPermission;
import com.linck.management.system.entity.SysUser;
import com.linck.management.system.service.SysPermissionService;
import com.linck.management.system.service.SysUserService;
import com.linck.management.system.vo.SysPermissionVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: MyManagement
 * @description
 * @author: Linck
 * @create: 2020-08-09 17:43
 **/
@Api(tags = "系统用户控制器")
@RestController
@RequestMapping("/sys/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SysPermissionService sysPermissionService;
    @Autowired
    private SysUserService sysUserService;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public CommonResult login(@RequestBody @Validated LoginUserDTO userDetail, HttpServletRequest request) {
        /*if(!userDetail.getVerificationCode().equals(request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY))){
            return CommonResult.validateFailed("验证码错误");
        }*/
        String token = sysUserService.login(userDetail);
        if (token == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", tokenHead + " " + token);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public CommonResult register(@RequestBody @Validated LoginUserDTO userDetail) {
        SysUser sysUser = sysUserService.register(userDetail);
        if (sysUser == null) {
            return CommonResult.failed("该用户名已经被注册");
        }
        sysUser.setPwd(null);
        return CommonResult.success(sysUser);
    }

    // 示例：需要权限user:view进行访问
    // @PreAuthorize("hasAuthority('user:view')")
    @ApiOperation("查询用户菜单和按钮")
    @PostMapping("/query/menu")
    public CommonResult<List<SysPermissionVO>> queryPermission() {
        // 从SecurityContextHolder获取当前用户
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
        SysUser sysUser = sysUserDetails.getSysUser();
        // 查询当前用户权限
        List<SysPermission> sysPermissions = sysPermissionService.listByUserId(sysUser.getId());
        // 封装菜单和按钮返回前端
        List<SysPermissionVO> resultPermissions = new ArrayList<>();
        sysPermissions.forEach(sysPermission -> {
            // 权限行不返回前端
            if (!sysPermission.getType().equals(SysPermissionTypeEnum.PERMISSION.getType())) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                resultPermissions.add(sysPermissionVO);
            }
        });
        return CommonResult.success(resultPermissions);
    }

}
