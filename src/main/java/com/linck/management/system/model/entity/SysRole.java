package com.linck.management.system.model.entity;

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
 * 系统角色
 *
 * @author linck
 * @create 2020-11-04
 */
@Data
@ApiModel(value = "SysRole对象", description = "系统角色")
public class SysRole {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "英文值")
    private String value;

    @ApiModelProperty(value = "中文名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @JsonDeserialize(using = StateDeserializer.class)
    @ApiModelProperty(value = "状态 1-启用 0-禁用")
    private StateEnum state;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
