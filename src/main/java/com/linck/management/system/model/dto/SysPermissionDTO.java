package com.linck.management.system.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linck.management.common.handler.jackson.StateDeserializer;
import com.linck.management.common.model.constant.StateEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-27 21:49
 **/
@Data
public class SysPermissionDTO {

    @ApiModelProperty("主键id")
    private Long id;

    @ApiModelProperty("父级权限id")
    @NotNull(message = "父级权限不能为空")
    private Long pid;

    @ApiModelProperty("名称")
    @NotBlank(message = "名称不能为空")
    private String name;

    @ApiModelProperty("内容")
    private String value;

    @ApiModelProperty("类型 1-菜单 2-按钮 3-权限")
    @NotNull(message = "类型不能为空")
    private Integer type;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("前端路由")
    private String url;

    @JsonDeserialize(using = StateDeserializer.class)
    @ApiModelProperty("启用状态(1-启用 0-禁用)")
    private StateEnum state;

    @ApiModelProperty("排序")
    private Integer sort;

}
