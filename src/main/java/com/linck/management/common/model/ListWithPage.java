package com.linck.management.common.model;

import lombok.Data;

import java.util.List;

/**
 * @program: management
 * @description List包含总条数信息
 * @author: linck
 * @create: 2020-11-02 22:35
 **/
@Data
public class ListWithPage<T> {
    private Long total;
    private List<T> list;
}
