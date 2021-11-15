package com.linck.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.system.model.dto.SysUserSearchDTO;
import com.linck.management.system.model.entity.SysUser;

import java.util.List;

/**
 * @author linck
 * @create 2020-08-09
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 用户列表
     * 未返回密码
     *
     * @param sysUserSearchDTO
     * @return
     */
    List<SysUser> listWithSearch(SysUserSearchDTO sysUserSearchDTO);
}
