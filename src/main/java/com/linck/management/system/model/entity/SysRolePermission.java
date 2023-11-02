package com.linck.management.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linck.management.common.model.BaseEntity;
import com.linck.management.common.validate.Insert;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Null;
import java.io.Serial;
import java.io.Serializable;

/**
 * @author linck
 * @date 2023-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role_permission")
@ApiModel(value="SysRolePermission对象", description="")
public class SysRolePermission extends BaseEntity implements Serializable {

    public SysRolePermission(Long id, Long rId, Long pId) {
        this.id = id;
        this.rId = rId;
        this.pId = pId;
    }

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    @Null(groups = Insert.class, message = "新增时id需要为空")
    private Long id;

    @ApiModelProperty(value = "角色id")
    private Long rId;

    @ApiModelProperty(value = "权限id")
    private Long pId;

}
