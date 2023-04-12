package com.linck.management.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.linck.management.common.model.BaseEntity;
import com.linck.management.common.model.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author linck
 * @create 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysPermission extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父级权限id
     */
    private Long pid;

    /**
     * 名称
     */
    private String name;

    /**
     * 内容
     */
    private String value;

    /**
     * 类型 1-菜单 2-按钮 3-权限
     */
    private Integer type;

    /**
     * 图标
     */
    private String icon;

    /**
     * 前端资源路径
     */
    private String url;

    /**
     * 状态 1-启用 0-禁用
     */
    private StatusEnum status;

    /**
     * 排序
     */
    private Integer sort;

}
