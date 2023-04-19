package com.linck.management.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.linck.management.common.validate.Insert;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Null;

/**
 * SysUserRole对象
 *
 * @author linck
 * @date 2020-11-04
 */
@Data
@AllArgsConstructor
public class SysUserRole {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Null(groups = Insert.class, message = "新增时id需要为空")
    private Long id;

    /**
     * 用户id
     */
    private Long uId;

    /**
     * 角色id
     */
    private Long rId;

}
