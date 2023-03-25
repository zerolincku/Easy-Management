package com.linck.management.system.model.vo;

import com.linck.management.common.model.enums.StatusEnum;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author linck
 **/
@Data
public class SysMenuAndButton {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 父级菜单id
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
     * 类型1-菜单 2-按钮 3-权限
     */
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
     * 排序
     */
    private Integer sort;

    private StatusEnum status;

    /**
     * 二级按钮
     */
    public List<SysPermissionVO> childrenList;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

}
