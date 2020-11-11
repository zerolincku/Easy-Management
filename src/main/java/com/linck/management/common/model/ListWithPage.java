package com.linck.management.common.model;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-02 22:35
 **/
public class ListWithPage<T> {

    private Long total;
    private T list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getList() {
        return list;
    }

    public void setList(T list) {
        this.list = list;
    }
}
