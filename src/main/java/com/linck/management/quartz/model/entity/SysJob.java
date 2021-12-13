package com.linck.management.quartz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linck.management.common.handler.jackson.StateDeserializer;
import com.linck.management.common.model.constant.StateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author linck
 * @create 2020-11-04
 */
@Data
@ApiModel(value = "SysJob对象")
public class SysJob {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "job类路径")
    private String jobClass;

    @ApiModelProperty(value = "job名字")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "cron")
    private String cron;

    @JsonDeserialize(using = StateDeserializer.class)
    @ApiModelProperty(value = "启用状态(1-启用 0-禁用)")
    private StateEnum state;

    @ApiModelProperty(value = "触发器name")
    private String triggerName;

    @ApiModelProperty(value = "组")
    private String groupName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
