package com.linck.management.system.model.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-10-21 23:43
 **/
public class SysMenuAndButton {
    /**
     * 一级折叠菜单
     */
    @ApiModelProperty(value = "一级折叠菜单")
    public SysPermissionVO menu;
    /**
     * 二级按钮
     */
    @ApiModelProperty(value = "二级按钮")
    public List<SysPermissionVO> buttons;

    public SysPermissionVO getMenu() {
        return menu;
    }

    public void setMenu(SysPermissionVO menu) {
        this.menu = menu;
    }

    public List<SysPermissionVO> getButtons() {
        return buttons;
    }

    public void setButtons(List<SysPermissionVO> buttons) {
        this.buttons = buttons;
    }
}
