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
@TableName("sys_role")
@ApiModel(value="SysRole对象", description="系统角色")
public class SysRole extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    @Null(groups = Insert.class, message = "新增时id需要为空")
    private Long id;

    @ApiModelProperty(value = "编码值")
    private String value;

    @ApiModelProperty(value = "中文名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

}
