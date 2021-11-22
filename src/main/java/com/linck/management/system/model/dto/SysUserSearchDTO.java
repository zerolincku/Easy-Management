package com.linck.management.system.model.dto;

import com.linck.management.common.model.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-15 20:57
 **/
@Data
public class SysUserSearchDTO extends PageDTO {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "状态 1-启用 0-禁用")
    private Integer state;

}
