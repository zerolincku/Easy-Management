package com.linck.management.common.model.dto;

import lombok.Setter;

/**
 * 分页参数
 * @author linck
 **/
@Setter
public class PageDto {

    /**
     * 第几页
     */
    private Integer pageNum;

    /**
     * 每页条数
     */
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
