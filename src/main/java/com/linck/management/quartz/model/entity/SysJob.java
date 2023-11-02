package com.linck.management.quartz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linck.management.common.model.BaseEntity;
import com.linck.management.common.model.enums.StatusEnum;
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
@TableName("sys_job")
@ApiModel(value="SysJob对象", description="任务")
public class SysJob extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    @Null(groups = Insert.class, message = "新增时id需要为空")
    private Long id;

    @ApiModelProperty(value = "job类路径")
    private String jobClass;

    @ApiModelProperty(value = "job名字")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "cron")
    private String cron;

    @ApiModelProperty(value = "启用状态(1-启用 0-禁用)")
    private StatusEnum status;

    @ApiModelProperty(value = "触发器name")
    private String triggerName;

    @ApiModelProperty(value = "组")
    private String groupName;

}
