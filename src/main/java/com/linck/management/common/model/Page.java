package com.linck.management.common.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-11-01 21:28
 **/
public class Page {

    @ApiModelProperty("页数")
    private Integer pageNum;

    @ApiModelProperty("每页条数")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
