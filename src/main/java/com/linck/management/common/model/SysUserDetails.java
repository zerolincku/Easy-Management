package com.linck.management.common.model;

import com.linck.management.system.model.entity.SysPermission;
import com.linck.management.system.model.entity.SysUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author linck
 **/
public class SysUserDetails implements UserDetails {

    private final SysUser sysUser;

    private final List<SysPermission> permissionList;

    /**
     * 获取当前登录用户信息
     */
    public SysUser getSysUser() {
        return sysUser;
    }

    public SysUserDetails(SysUser sysUser, List<SysPermission> permissionList) {
        this.sysUser = sysUser;
        this.permissionList = permissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissionList.stream()
                .filter(permission -> permission.getValue() != null)
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return sysUser.getPwd();
    }

    @Override
    public String getUsername() {
        return sysUser.getAccount();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !sysUser.getDelFlag();
    }

}
