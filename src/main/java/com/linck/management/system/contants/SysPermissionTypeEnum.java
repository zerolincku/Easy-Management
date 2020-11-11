package com.linck.management.system.contants;

/**
 * @program: MyManagement
 * @description
 * @author: linck
 * @create: 2020-08-10 19:11
 **/
public enum SysPermissionTypeEnum {

    /**
     * 菜单
     */
    MENU(1),
    /**
     * 按钮
     */
    BUTTON(2),
    /**
     * 权限
     */
    PERMISSION(3);

    private Integer type;

    SysPermissionTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
