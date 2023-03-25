package com.linck.management.common.model.vo;

import lombok.Data;

import java.util.List;

/**
 * List包含总条数信息
 * @author linck
 **/
@Data
public class ListWithPage<T> {

    /**
     * 总条数
     */
    private Long total;

    /**
     * 数据列表
     */
    private List<T> list;

}
