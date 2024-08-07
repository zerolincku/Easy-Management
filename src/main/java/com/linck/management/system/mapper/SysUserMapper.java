package com.linck.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.system.model.dto.SysUserSearchDto;
import com.linck.management.system.model.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linck
 * @date 2020-08-09
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 用户列表
     * 未返回密码
     */
    List<SysUser> listWithSearch(SysUserSearchDto sysUserSearchDto);
}
