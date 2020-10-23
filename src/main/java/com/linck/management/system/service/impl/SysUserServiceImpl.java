package com.linck.management.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linck.management.common.util.JwtTokenUtils;
import com.linck.management.system.entity.SysUser;
import com.linck.management.system.mapper.SysRoleMapper;
import com.linck.management.system.mapper.SysUserMapper;
import com.linck.management.system.model.dto.LoginUserDTO;
import com.linck.management.system.service.SysUserService;
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

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author linck
 * @since 2020-08-09
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    private static final Logger log = LoggerFactory.getLogger(SysUserServiceImpl.class);

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
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 登录功能
     *
     * @param loginUser
     * @return 生成JWT的Token
     */
    @Override
    public String login(LoginUserDTO loginUser) {
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
    @Override
    public SysUser register(LoginUserDTO loginUser) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(loginUser, sysUser);
        //查询是否有相同用户名的用户
        int count = sysUserMapper.selectCount(new QueryWrapper<SysUser>().eq("account", sysUser.getAccount()));
        if (count > 0) {
            return null;
        }
        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(sysUser.getPwd());
        sysUser.setPwd(encodePassword);
        sysUserMapper.insert(sysUser);
        log.debug("注册用户成功");
        return sysUser;
    }
}
