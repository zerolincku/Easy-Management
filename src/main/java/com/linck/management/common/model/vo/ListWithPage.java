package com.linck.management.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * List包含总条数信息
 * @author linck
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListWithPage<T> {

    /**
     * 数据列表
     */
    private List<T> list;

    /**
     * 总条数
     */
    private Long total;

}
