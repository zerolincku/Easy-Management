package com.linck.management.common.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-11 22:45
 **/
@Data
public class StateDTO {
    @ApiModelProperty(value = "状态 1-启用 0-禁用")
    private Integer state;
}
