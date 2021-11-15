package com.linck.management.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author linck
 * @create 2020-11-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "SysRolePermission对象", description = "")
public class SysRolePermission {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "角色id")
    private Long rId;

    @ApiModelProperty(value = "权限id")
    private Long pId;

}
