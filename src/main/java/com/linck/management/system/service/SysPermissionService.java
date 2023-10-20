package com.linck.management.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linck.management.common.exception.BizException;
import com.linck.management.common.model.enums.StatusEnum;
import com.linck.management.system.contants.SysPermissionTypeEnum;
import com.linck.management.system.mapper.SysPermissionMapper;
import com.linck.management.system.model.entity.SysPermission;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.model.vo.SysPermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author linck
 * @date 2020-08-09
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "permission")
public class SysPermissionService extends ServiceImpl<SysPermissionMapper, SysPermission> {

    /**
     * 根据UserId查询所有启用状态权限
     */
    public List<SysPermission> listByUserId(Long id) {
        return baseMapper.listByUserId(id);
    }

    /**
     * 查询权限列表
     */
    @Cacheable(key = "'all'")
    public List<SysPermission> listAll(StatusEnum statusEnum) {
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        if (statusEnum != null) {
            wrapper.eq("status", statusEnum);
        }
        wrapper.orderByAsc("type", "sort");
        return baseMapper.selectList(wrapper);
    }

    /**
     * 查询菜单和按钮
     */
    @Cacheable(key = "'tree:all'")
    public List<SysMenuAndButton> allMenuAndButton() {
        List<SysPermission> sysPermissions = listAll(StatusEnum.ENABLE);
        List<SysMenuAndButton> result = new ArrayList<>();
        sysPermissions.forEach(sysPermission -> {
            // 封装一级折叠菜单
            if (sysPermission.getType().equals(SysPermissionTypeEnum.MENU)) {
                SysMenuAndButton menuAndButton = new SysMenuAndButton();
                BeanUtils.copyProperties(sysPermission, menuAndButton);
                result.add(menuAndButton);
                // 封装二级按钮
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.BUTTON)) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                Optional<SysMenuAndButton> optional = result.stream().filter(t -> t.getId().equals(sysPermissionVO.getPid())).findFirst();
                if (optional.get().getChildrenList() == null) {
                    optional.get().setChildrenList(new ArrayList<>());
                }
                optional.get().getChildrenList().add(sysPermissionVO);
                // 封装权限
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.PERMISSION)) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                Optional<SysPermission> button = sysPermissions.stream().filter(t -> t.getId().equals(sysPermissionVO.getPid())).findFirst();
                Optional<SysPermission> menu = sysPermissions.stream().filter(t -> t.getId().equals(button.get().getPid())).findFirst();
                Optional<SysMenuAndButton> resultMenu = result.stream().filter(t -> t.getId().equals(menu.get().getId())).findFirst();
                Optional<SysPermissionVO> resultButton = resultMenu.get().getChildrenList().stream().filter(t -> t.getId().equals(button.get().getId())).findFirst();
                if (resultButton.get().getChildrenList() == null) {
                    resultButton.get().setChildrenList(new ArrayList<>());
                }
                resultButton.get().getChildrenList().add(sysPermissionVO);
            }
        });
        return result;
    }

    /**
     * 查询用户菜单和按钮
     */
    @Cacheable(key = "'userId:' + #userId")
    public List<SysMenuAndButton> getMenuAndButtonByUserId(Long userId) {
        // 查询当前用户权限
        List<SysPermission> sysPermissions = listByUserId(userId);
        // 封装菜单和按钮返回前端
        List<SysMenuAndButton> result = new ArrayList<>();
        sysPermissions.forEach(sysPermission -> {
            // 封装一级折叠菜单
            if (sysPermission.getType().equals(SysPermissionTypeEnum.MENU)) {
                SysMenuAndButton menuAndButton = new SysMenuAndButton();
                BeanUtils.copyProperties(sysPermission, menuAndButton);
                result.add(menuAndButton);
                // 封装二级按钮
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.BUTTON)) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                Optional<SysMenuAndButton> optional = result.stream().filter(t -> t.getId().equals(sysPermissionVO.getPid())).findFirst();
                if (optional.get().getChildrenList() == null) {
                    optional.get().setChildrenList(new ArrayList<>());
                }
                optional.get().getChildrenList().add(sysPermissionVO);
            }
        });
        return result;
    }

    /**
     * 新增权限
     */
    @CacheEvict(allEntries = true)
    @Override
    public boolean save(SysPermission sysPermission) {
        if (SysPermissionTypeEnum.MENU.equals(sysPermission.getType())) {
            Long count = baseMapper.selectCount(new QueryWrapper<SysPermission>().eq("name", sysPermission.getName()));
            if (count > 0) {
                throw new BizException("当前菜单名称已经存在");
            }
        } else if (SysPermissionTypeEnum.BUTTON.equals(sysPermission.getType())) {
            Long count = baseMapper.selectCount(new QueryWrapper<SysPermission>().eq("pid", sysPermission.getPid()).eq("url", sysPermission.getUrl()));
            if (count > 0) {
                throw new BizException("当前按钮url已经存在");
            }
        } else if (SysPermissionTypeEnum.PERMISSION.equals(sysPermission.getType())) {
            Long count = baseMapper.selectCount(new QueryWrapper<SysPermission>().eq("pid", sysPermission.getPid()).eq("value", sysPermission.getUrl()));
            if (count > 0) {
                throw new BizException("当前权限内容已经存在");
            }
        }
        baseMapper.insert(sysPermission);
        return true;
    }


}
