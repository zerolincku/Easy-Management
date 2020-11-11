package com.linck.management.common.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-04 23:10
 **/
public class IdModel {

    @ApiModelProperty("主键id")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
