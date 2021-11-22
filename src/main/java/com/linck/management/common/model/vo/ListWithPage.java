package com.linck.management.common.model.vo;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("总条数")
    private Long total;

    @ApiModelProperty("数据列表")
    private List<T> list;

}
