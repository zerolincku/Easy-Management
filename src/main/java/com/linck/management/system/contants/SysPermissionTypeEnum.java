package com.linck.management.system.contants;

import com.fasterxml.jackson.annotation.JsonValue;
import com.linck.management.common.model.enums.NameValueInterface;

/**
 * @author linck
 **/
public enum SysPermissionTypeEnum implements NameValueInterface {

    MENU(1, "菜单"),

    BUTTON(2, "按钮"),

    PERMISSION(3, "权限");

    private final String name;
    private final Integer type;

    SysPermissionTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Integer getValue() {
        return type;
    }
}
