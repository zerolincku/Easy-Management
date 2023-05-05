package com.linck.management.system.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.SysUserDetails;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.model.dto.IdsDto;
import com.linck.management.common.model.vo.ListWithPage;
import com.linck.management.common.util.QueryCondition;
import com.linck.management.common.validate.Insert;
import com.linck.management.common.validate.Update;
import com.linck.management.system.model.dto.SysUserDto;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 查询系统用户列表
     */
    @GetMapping("page")
    public Result<ListWithPage<SysUser>> page(QueryCondition<SysUser> condition) {
        QueryWrapper<SysUser> queryWrapper = condition.dealQueryCondition(SysUser.class);
        Page<SysUser> page = sysUserService.page(condition.page(), queryWrapper);
        return Result.success(new ListWithPage<>(page.getRecords(), page.getTotal()));
    }

    /**
     * 新增系统用户
     */
    @PostMapping
    public Result<String> add(@RequestBody @Validated(Insert.class) SysUser sysUser) {
        sysUserService.save(sysUser);
        return Result.success();
    }

    /**
     * 修改系统用户
     */
    @PutMapping
    public Result<String> update(@RequestBody @Validated(Update.class) SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return Result.success();
    }

    /**
     * 删除系统用户
     */
    @DeleteMapping
    public Result<String> remove(@RequestBody @Validated IdsDto idsDto) {
        sysUserService.removeBatchByIds(idsDto.getIds());
        return Result.success();
    }

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
    public Result<String> register(@RequestBody @Validated SysUserDto userDetail) {
        sysUserService.register(userDetail);
        return Result.success();
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
    public Result<String> saveRoleList(@RequestBody @Validated UserRoleSaveModel model) {
        sysUserService.saveRoleList(model);
        return Result.success();
    }

}
