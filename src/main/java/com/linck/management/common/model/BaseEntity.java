package com.linck.management.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 基础实体类，包含通用字段
 *
 * @author linck
 * @date 2023-04-11
 */
@Data
public class BaseEntity {

    /**
     * 创建用户id
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createAt;

    /**
     * 更新用户id
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateAt;

    /**
     * 逻辑删除标志 false-未删除 1-已删除
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Boolean delFlag;

    /**
     * 版本号，用于乐观锁
     */
    @Version
    @TableField(fill = FieldFill.INSERT)
    private Long version;
}
