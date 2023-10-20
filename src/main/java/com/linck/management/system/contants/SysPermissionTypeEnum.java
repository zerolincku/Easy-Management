package com.linck.management.system.contants;

import com.linck.management.common.model.enums.NameValueInterface;

/**
 * @author linck
 **/
public enum SysPermissionTypeEnum implements NameValueInterface {

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

    @Override
    public String getName() {
        return this.name();
    }

    @Override
    public Integer getValue() {
        return type;
    }
}
