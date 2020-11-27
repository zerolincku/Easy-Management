package com.linck.management.common.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-04 23:10
 **/
@Data
public class IdModel {

    @ApiModelProperty("主键id")
    @NotNull(message = "id不能为空")
    private Long id;

}
