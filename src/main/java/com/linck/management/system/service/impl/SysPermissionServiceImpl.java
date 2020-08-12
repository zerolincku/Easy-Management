package com.linck.management.system.service.impl;

import com.linck.management.system.entity.SysPermission;
import com.linck.management.system.mapper.SysPermissionMapper;
import com.linck.management.system.service.SysPermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author linck
 * @since 2020-08-09
 */
@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    private static final Logger log = LoggerFactory.getLogger(SysPermissionServiceImpl.class);

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 根据UserId查询所有启用状态权限
     * @param id
     * @return
     */
    @Override
    public List<SysPermission> listByUserId(Long id) {
        return sysPermissionMapper.listByUserId(id);
    }
}
