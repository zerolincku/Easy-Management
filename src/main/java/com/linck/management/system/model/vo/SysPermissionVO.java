package com.linck.management.system.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linck.management.common.config.StateDeserializer;
import com.linck.management.common.model.constant.StateEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @program: MyManagement
 * @description
 * @author: linck
 * @create: 2020-08-10 19:08
 **/
@Data
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

    @JsonDeserialize(using = StateDeserializer.class)
    private StateEnum state;

    @ApiModelProperty(value = "权限集合")
    public List<SysPermissionVO> childrenList;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
