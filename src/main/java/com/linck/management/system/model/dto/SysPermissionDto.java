package com.linck.management.system.model.dto;

import com.linck.management.common.model.enums.StatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author linck
 **/
@Data
public class SysPermissionDto {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 父级权限id
     */
    @NotNull(message = "父级权限不能为空")
    private Long pid;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空")
    private String name;

    /**
     * 内容
     */
    private String value;

    /**
     * 类型 1-菜单 2-按钮 3-权限
     */
    @NotNull(message = "类型不能为空")
    private Integer type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 前端路由
     */
    private String url;

    /**
     * 启用状态(1-启用 0-禁用)
     */
    private StatusEnum status;

    /**
     * 排序
     */
    private Integer sort;

}
