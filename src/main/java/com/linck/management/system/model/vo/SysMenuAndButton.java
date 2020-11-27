package com.linck.management.system.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-10-21 23:43
 **/
@Data
public class SysMenuAndButton {

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

    @ApiModelProperty(value = "二级按钮")
    public List<SysPermissionVO> childrenList;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
