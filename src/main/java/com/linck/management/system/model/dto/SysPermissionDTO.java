package com.linck.management.system.model.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-10-27 21:49
 **/
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

    @ApiModelProperty("状态 1-启用 0-禁用")
    private Integer state;

    @ApiModelProperty("排序")
    private Integer sort;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}