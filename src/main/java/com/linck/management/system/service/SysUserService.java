package com.linck.management.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.IdModel;
import com.linck.management.common.util.JwtTokenUtils;
import com.linck.management.system.entity.SysRole;
import com.linck.management.system.entity.SysUser;
import com.linck.management.system.entity.SysUserRole;
import com.linck.management.system.mapper.SysRoleMapper;
import com.linck.management.system.mapper.SysUserMapper;
import com.linck.management.system.mapper.SysUserRoleMapper;
import com.linck.management.system.model.dto.SysUserDTO;
import com.linck.management.system.model.dto.SysUserSearchDTO;
import com.linck.management.system.model.vo.UserRoleModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
 * @create 2020-08-09
 */
@Service
public class SysUserService extends ServiceImpl<SysUserMapper, SysUser> {

    private static final Logger log = LoggerFactory.getLogger(SysUserService.class);

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登录功能
     *
     * @param loginUser
     * @return 生成JWT的Token
     */
    public String login(SysUserDTO loginUser) {
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
     *
     * @param loginUser
     * @return
     */
    public SysUser register(SysUserDTO loginUser) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(loginUser, sysUser);
        // 查询是否有相同用户名的用户
        int count = sysUserMapper.selectCount(new QueryWrapper<SysUser>().eq("account", sysUser.getAccount()));
        if (count > 0) {
            return null;
        }
        // 将密码进行加密操作
        String encodePassword = passwordEncoder.encode(sysUser.getPwd());
        sysUser.setPwd(encodePassword);
        sysUserMapper.insert(sysUser);
        log.debug("注册用户成功");
        return sysUser;
    }

    /**
     * 分页查询用户
     *
     * @param sysUserSearchDTO
     * @return
     */
    public List<SysUser> selectList(SysUserSearchDTO sysUserSearchDTO) {
        PageHelper.startPage(sysUserSearchDTO.getPageNum(), sysUserSearchDTO.getPageSize());
        return sysUserMapper.listWithSearch(sysUserSearchDTO);
    }

    public Result add(SysUser sysUser) {
        Integer count = sysUserMapper.selectCount(new QueryWrapper<SysUser>().eq("account", sysUser.getAccount()));
        if (count > 0) {
            return Result.failed("当前账户已经存在");
        }
        return Result.success(sysUserMapper.insert(sysUser));
    }

    /**
     * 查询用户角色
     *
     * @param model
     * @return
     */
    public Result<List<UserRoleModel>> roleList(IdModel model) {
        List<SysRole> sysRoles = sysRoleMapper.selectList(null);
        List<Long> roleIdList = sysUserRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("u_id", model.getId())).stream().map(t -> t.getrId()).collect(Collectors.toList());
        List<UserRoleModel> result = sysRoles.stream().map(t -> new UserRoleModel(t.getId(), t.getName(), roleIdList.contains(t.getId()))).collect(Collectors.toList());
        return Result.success(result);
    }
}
