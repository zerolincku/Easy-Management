package com.linck.management.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author linck
 * @create 2020-11-04
 */
@Data
@AllArgsConstructor
@ApiModel(value = "SysUserRole对象")
public class SysUserRole {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long uId;

    @ApiModelProperty(value = "角色id")
    private Long rId;

}
