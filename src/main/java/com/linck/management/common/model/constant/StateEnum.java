package com.linck.management.common.model.constant;

import lombok.Getter;

/**
 * @program: MyManagement
 * @description 通用状态枚举
 * @author: linck
 * @create: 2020-08-09 17:20
 **/
@Getter
public enum StateEnum {

    /**
     * 启用
     */
    ENABLE(1),
    /**
     * 禁用
     */
    DISABLE(0);

    private Integer value;

    StateEnum(Integer value) {
        this.value = value;
    }
}
