package com.linck.management.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SysRolePermission对象
 *
 * @author linck
 * @date 2020-11-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysRolePermission {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色id
     */
    private Long rId;

    /**
     * 权限id
     */
    private Long pId;

}
