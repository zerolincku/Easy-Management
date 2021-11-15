package com.linck.management.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.linck.management.system.model.entity.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author linck
 * @create 2020-08-09
 */
@Repository
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    Integer insertList(List<SysUserRole> list);
}
