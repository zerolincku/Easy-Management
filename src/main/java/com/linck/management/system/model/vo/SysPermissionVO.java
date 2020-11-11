package com.linck.management.system.model.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * @program: MyManagement
 * @description
 * @author: Linck
 * @create: 2020-08-10 19:08
 **/
public class SysPermissionVO {
    
    @ApiModelProperty(value = "主键id")
    private Long id;

    
    @ApiModelProperty(value = "父级菜单id")
    private Long pid;

    
    @ApiModelProperty(value = "名称")
    private String name;

    
    @ApiModelProperty(value = "内容")
    private String value;

    
    @ApiModelProperty(value = "类型1-菜单 2-按钮 3-权限")
    private Integer type;

    
    @ApiModelProperty(value = "图标")
    private String icon;

    
    @ApiModelProperty(value = "前端路由")
    private String url;
    
    @ApiModelProperty(value = "排序")
    private Integer sort;
    
    private Integer state;
    
    @ApiModelProperty(value = "权限集合")
    public List<SysPermissionVO> childrenList;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
