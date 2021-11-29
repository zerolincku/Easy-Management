package com.linck.management.common.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linck.management.common.config.StateDeserializer;
import com.linck.management.common.model.constant.StateEnum;
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
    @JsonDeserialize(using = StateDeserializer.class)
    @ApiModelProperty(value = "状态 1-启用 0-禁用")
    private StateEnum state;
}
