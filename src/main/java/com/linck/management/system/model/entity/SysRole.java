package com.linck.management.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.linck.management.common.model.BaseEntity;
import com.linck.management.common.model.enums.StatusEnum;
import com.linck.management.common.validate.Insert;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.Null;

/**
 * 系统角色
 *
 * @author linck
 * @date 2020-11-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @Null(groups = Insert.class, message = "新增时id需要为空")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 英文值
     */
    private String value;

    /**
     * 中文名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 状态 1-启用 0-禁用
     */
    private StatusEnum status;

}
