package com.linck.management.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * SysUserRole对象
 *
 * @author linck
 * @create 2020-11-04
 */
@Data
@AllArgsConstructor
public class SysUserRole {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
