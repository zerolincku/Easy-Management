package com.linck.management.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linck.management.common.api.Result;
import com.linck.management.common.exception.BizException;
import com.linck.management.common.model.dto.IdDto;
import com.linck.management.common.util.JwtTokenUtils;
import com.linck.management.system.mapper.SysRoleMapper;
import com.linck.management.system.mapper.SysUserMapper;
import com.linck.management.system.model.dto.SysUserDto;
import com.linck.management.system.model.dto.UserRoleSaveModel;
import com.linck.management.system.model.entity.SysRole;
import com.linck.management.system.model.entity.SysUser;
import com.linck.management.system.model.entity.SysUserRole;
import com.linck.management.system.model.vo.UserRoleModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linck
 * @date 2020-08-09
 */
@Slf4j
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {
    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    /**
     * 登录功能
     *
     * @return 生成JWT的Token
     */
    public String login(SysUserDto loginUser) {
        String token = null;
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(loginUser.getAccount());
            if (!passwordEncoder.matches(loginUser.getPassword(), userDetails.getPassword())) {
                throw new BadCredentialsException("用户名或密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtils.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    /**
     * 注册功能
     */
    public SysUser register(SysUserDto loginUser) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(loginUser, sysUser);
        // 查询是否有相同用户名的用户
        Long count = sysUserMapper.selectCount(new QueryWrapper<SysUser>().eq("account", sysUser.getAccount()));
        if (count > 0) {
            throw new BizException("该账号已经被注册");
        }
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(sysUser.getPwd());
        sysUser.setPwd(encodePassword);
        sysUserMapper.insert(sysUser);
        log.debug("注册用户成功");
        return sysUser;
    }

    @Override
    public boolean save(SysUser sysUser) {
        Long count = sysUserMapper.selectCount(new QueryWrapper<SysUser>().eq("account", sysUser.getAccount()));
        if (count > 0) {
            throw new BizException("当前账户已经存在");
        }
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(sysUser.getPwd());
        sysUser.setPwd(encodePassword);
        sysUserMapper.insert(sysUser);
        return true;
    }

    /**
     * 查询用户角色
     */
    public Result<List<UserRoleModel>> roleList(IdDto model) {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        List<Long> roleIdList = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("u_id", model.getId())).stream().map(SysUserRole::getRId).collect(Collectors.toList());
        List<UserRoleModel> result = sysRoles.stream().map(t -> new UserRoleModel(t.getId(), t.getName(), roleIdList.contains(t.getId()))).collect(Collectors.toList());
        return Result.success(result);
    }

    /**
     * 保存用户角色
     */
    public void saveRoleList(UserRoleSaveModel model) {
        List<Long> databaseRoleIdList = sysUserRoleService.list(new QueryWrapper<SysUserRole>().eq("u_id", model.getUserId())).stream().map(SysUserRole::getRId).collect(Collectors.toList());
        List<SysUserRole> addList = model.getRoleIdList().stream().filter(t -> !databaseRoleIdList.contains(t)).map(t -> new SysUserRole(null, model.getUserId(), t)).collect(Collectors.toList());
        List<Long> deleteIdList = databaseRoleIdList.stream().filter(t -> !model.getRoleIdList().contains(t)).collect(Collectors.toList());
        if (!deleteIdList.isEmpty()) {
            sysUserRoleService.remove(new QueryWrapper<SysUserRole>().eq("u_id", model.getUserId()).in("r_id", deleteIdList));
        }
        if (!addList.isEmpty()) {
            sysUserRoleService.saveBatch(addList);
        }
    }
}
