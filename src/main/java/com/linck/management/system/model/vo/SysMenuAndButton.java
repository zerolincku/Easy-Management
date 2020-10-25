package com.linck.management.system.model.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-10-21 23:43
 **/
public class SysMenuAndButton {
    /**
     * 主键id
     */
    @ApiModelProperty(value = "主键id")
    private Long id;

    /**
     * 父级权限id
     */
    @ApiModelProperty(value = "父级菜单id")
    private Long pid;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;

    /**
     * 内容
     */
    @ApiModelProperty(value = "内容")
    private String value;

    /**
     * 类型 1-菜单 2-按钮 3-权限
     */
    @ApiModelProperty(value = "类型1-菜单 2-按钮 3-权限")
    private Integer type;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 前端路由
     */
    @ApiModelProperty(value = "前端路由")
    private String url;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 二级按钮
     */
    @ApiModelProperty(value = "二级按钮")
    public List<SysPermissionVO> childrenList;

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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<SysPermissionVO> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<SysPermissionVO> childrenList) {
        this.childrenList = childrenList;
    }
}
