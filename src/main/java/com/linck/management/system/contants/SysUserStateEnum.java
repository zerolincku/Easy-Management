package com.linck.management.system.contants;

/**
 * @program: MyManagement
 * @description
 * @author: Linck
 * @create: 2020-08-09 17:20
 **/
public enum SysUserStateEnum {

    /**
     * 启用
     */
    ENABLE(1),
    /**
     * 禁用
     */
    DISENABLE(0);

    private Integer state;

    SysUserStateEnum(Integer state){
        this.state = state;
    }

    public Integer getState() {
        return state;
    }
}
