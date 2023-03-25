package com.linck.management.system.contants;

/**
 * @author linck
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

    private final Integer type;

    SysPermissionTypeEnum(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
