package com.linck.management.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: management
 * @description 分页参数
 * @author: linck
 * @create: 2020-11-01 21:28
 **/
@Data
public class Page {

    @ApiModelProperty("页数")
    private Integer pageNum;

    @ApiModelProperty("每页条数")
    private Integer pageSize;

    public void ifNotPageSetDefault() {
        if (pageNum == null || pageSize == null) {
            pageNum = 1;
            pageSize = 20;
        }
    }
}
