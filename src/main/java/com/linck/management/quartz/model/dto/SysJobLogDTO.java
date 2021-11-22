package com.linck.management.quartz.model.dto;

import com.linck.management.common.model.dto.PageDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-29 21:40
 **/
@Data
public class SysJobLogDTO extends PageDTO {

    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "job的Id")
    private Long jobId;

    @ApiModelProperty(value = "描述")
    private String msg;

    @ApiModelProperty(value = "结果 1-成功 0-失败")
    private Integer result;
}
