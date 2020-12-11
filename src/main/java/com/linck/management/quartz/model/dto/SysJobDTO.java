package com.linck.management.quartz.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-29 21:40
 **/
@Data
public class SysJobDTO {

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

    @ApiModelProperty("启用状态(1-启用 0-禁用)")
    private Integer state;

    @ApiModelProperty("触发器name)")
    private String triggerName;

    @ApiModelProperty("触发器组")
    private String triggerGroup;

    private Integer pageNum;

    private Integer pageSize;

    public void ifNotPageSetDefault() {
        if (pageNum == null || pageSize == null) {
            pageNum = 1;
            pageSize = 1000;
        }
    }
}
