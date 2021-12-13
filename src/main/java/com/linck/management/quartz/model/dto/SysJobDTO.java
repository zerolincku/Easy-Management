package com.linck.management.quartz.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linck.management.common.handler.jackson.StateDeserializer;
import com.linck.management.common.model.constant.StateEnum;
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
public class SysJobDTO extends PageDTO {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("job类")
    private String jobClass;

    @ApiModelProperty("job名称")
    private String name;

    @ApiModelProperty("描述")
    private String description;

    @ApiModelProperty("cron")
    private String cron;

    @JsonDeserialize(using = StateDeserializer.class)
    @ApiModelProperty("启用状态(1-启用 0-禁用)")
    private StateEnum state;

    @ApiModelProperty("触发器name)")
    private String triggerName;

    @ApiModelProperty("组")
    private String group;

}
