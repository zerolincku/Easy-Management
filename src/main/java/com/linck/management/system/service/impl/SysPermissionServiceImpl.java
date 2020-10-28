package com.linck.management.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linck.management.system.contants.SysPermissionTypeEnum;
import com.linck.management.system.entity.SysPermission;
import com.linck.management.system.mapper.SysPermissionMapper;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.model.vo.SysPermissionVO;
import com.linck.management.system.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 服务实现类
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
     *
     * @param id
     * @return
     */
    @Override
    public List<SysPermission> listByUserId(Long id) {
        return sysPermissionMapper.listByUserId(id);
    }

    /**
     * 查询所有权限列表
     *
     * @return
     */
    @Override
    public List<SysPermission> listAll() {
        return sysPermissionMapper.selectList(new QueryWrapper<SysPermission>().orderByAsc("type", "sort"));
    }

    /**
     * 查询所有菜单和按钮
     */
    @Override
    public List<SysMenuAndButton> allMenuAndButton() {
        List<SysPermission> sysPermissions = listAll();
        List<SysMenuAndButton> result = new ArrayList<>();
        sysPermissions.forEach(sysPermission -> {
            // 封住一级折叠菜单
            if (sysPermission.getType().equals(SysPermissionTypeEnum.MENU.getType())) {
                SysMenuAndButton menuAndButton = new SysMenuAndButton();
                BeanUtils.copyProperties(sysPermission, menuAndButton);
                result.add(menuAndButton);
                // 封装二级按钮
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.BUTTON.getType())) {
                SysPermissionVO sysPermissionVO = new SysPermissionVO();
                BeanUtils.copyProperties(sysPermission, sysPermissionVO);
                Optional<SysMenuAndButton> optional = result.stream().filter(t -> t.getId().equals(sysPermissionVO.getPid())).findFirst();
                if (optional.get().getChildrenList() == null) {
                    optional.get().setChildrenList(new ArrayList<>());
                }
                optional.get().getChildrenList().add(sysPermissionVO);
                // 封装权限
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.PERMISSION.getType())) {
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
    @Override
    public List<SysMenuAndButton> getMenuAndButtonByUserId(Long userId) {
        // 查询当前用户权限
        List<SysPermission> sysPermissions = listByUserId(userId);
        // 封装菜单和按钮返回前端
        List<SysMenuAndButton> result = new ArrayList<>();
        sysPermissions.forEach(sysPermission -> {
            // 封住一级折叠菜单
            if (sysPermission.getType().equals(SysPermissionTypeEnum.MENU.getType())) {
                SysMenuAndButton menuAndButton = new SysMenuAndButton();
                BeanUtils.copyProperties(sysPermission, menuAndButton);
                result.add(menuAndButton);
                // 封装二级按钮
            } else if (sysPermission.getType().equals(SysPermissionTypeEnum.BUTTON.getType())) {
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
}
