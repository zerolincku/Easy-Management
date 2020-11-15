package com.linck.management.common.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-11 22:45
 **/
public class StateModel {
    @ApiModelProperty(value = "状态 1-启用 0-禁用")
    private Integer state;

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
