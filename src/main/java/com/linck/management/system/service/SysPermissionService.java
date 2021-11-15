package com.linck.management.system.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linck.management.common.api.Result;
import com.linck.management.common.model.StateModel;
import com.linck.management.system.contants.SysPermissionTypeEnum;
import com.linck.management.system.mapper.SysPermissionMapper;
import com.linck.management.system.model.entity.SysPermission;
import com.linck.management.system.model.vo.SysMenuAndButton;
import com.linck.management.system.model.vo.SysPermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author linck
 * @create 2020-08-09
 */
@Slf4j
@Service
public class SysPermissionService extends ServiceImpl<SysPermissionMapper, SysPermission> {

    @Autowired
    private SysPermissionMapper sysPermissionMapper;

    /**
     * 根据UserId查询所有启用状态权限
     *
     * @param id
     * @return
     */
    public List<SysPermission> listByUserId(Long id) {
        return sysPermissionMapper.listByUserId(id);
    }

    /**
     * 查询权限列表
     *
     * @return
     */
    public List<SysPermission> listAll(StateModel stateModel) {
        QueryWrapper<SysPermission> wrapper = new QueryWrapper<>();
        if (stateModel.getState() != null) {
            wrapper.eq("state", stateModel.getState());
        }
        wrapper.orderByAsc("type", "sort");
        return sysPermissionMapper.selectList(wrapper);
    }

    /**
     * 查询菜单和按钮
     */
    public List<SysMenuAndButton> allMenuAndButton(StateModel stateModel) {
        List<SysPermission> sysPermissions = listAll(stateModel);
        List<SysMenuAndButton> result = new ArrayList<>();
        sysPermissions.forEach(sysPermission -> {
            // 封装一级折叠菜单
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
    public List<SysMenuAndButton> getMenuAndButtonByUserId(Long userId) {
        // 查询当前用户权限
        List<SysPermission> sysPermissions = listByUserId(userId);
        // 封装菜单和按钮返回前端
        List<SysMenuAndButton> result = new ArrayList<>();
        sysPermissions.forEach(sysPermission -> {
            // 封装一级折叠菜单
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

    /**
     * 新增权限
     *
     * @param sysPermission
     * @return
     */
    public Result add(SysPermission sysPermission) {
        if (SysPermissionTypeEnum.MENU.getType().equals(sysPermission.getType())) {
            Integer count = sysPermissionMapper.selectCount(new QueryWrapper<SysPermission>().eq("name", sysPermission.getName()));
            if (count > 0) {
                return Result.failed("当前菜单名称已经存在");
            }
        } else if (SysPermissionTypeEnum.BUTTON.getType().equals(sysPermission.getType())) {
            Integer count = sysPermissionMapper.selectCount(new QueryWrapper<SysPermission>().eq("pid", sysPermission.getPid()).eq("url", sysPermission.getUrl()));
            if (count > 0) {
                return Result.failed("当前按钮url已经存在");
            }
        } else if (SysPermissionTypeEnum.PERMISSION.getType().equals(sysPermission.getType())) {
            Integer count = sysPermissionMapper.selectCount(new QueryWrapper<SysPermission>().eq("pid", sysPermission.getPid()).eq("value", sysPermission.getUrl()));
            if (count > 0) {
                return Result.failed("当前权限内容已经存在");
            }
        }
        sysPermissionMapper.insert(sysPermission);
        return Result.success(sysPermission.getId());
    }
}
