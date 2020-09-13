package com.linck.management.common.constant;

/**
 * @program: MyManagement
 * @description 通用状态枚举
 * @author: Linck
 * @create: 2020-08-09 17:20
 **/
public enum StateEnum {

    /**
     * 启用
     */
    ENABLE(1),
    /**
     * 禁用
     */
    DISENABLE(0);

    private Integer state;

    StateEnum(Integer state){
        this.state = state;
    }

    public Integer getState() {
        return state;
    }
}
