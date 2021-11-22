package com.linck.management.common.model.dto;

import lombok.Setter;

/**
 * @program: management
 * @description 分页参数
 * @author: linck
 * @create: 2020-11-01 21:28
 **/
@Setter
public class PageDTO {

    private Integer pageNum;

    private Integer pageSize;

    public Integer getPageNum() {
        if (pageNum == null) {
            return 1;
        }
        return pageNum;
    }

    public Integer getPageSize() {
        if (pageSize == null) {
            return 20;
        }
        return pageSize;
    }


}
