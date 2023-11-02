package com.linck.management.quartz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.linck.management.common.model.BaseEntity;
import com.linck.management.common.validate.Insert;
import com.linck.management.quartz.model.enums.JobResultEnum;
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
@TableName("sys_job_log")
@ApiModel(value="SysJobLog对象", description="任务日志")
public class SysJobLog extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    @Null(groups = Insert.class, message = "新增时id需要为空")
    private Long id;

    @ApiModelProperty(value = "JobId")
    private Long jobId;

    @ApiModelProperty(value = "描述")
    private String msg;

    @ApiModelProperty(value = "结果 1-成功 0-失败")
    private JobResultEnum result;

    @ApiModelProperty(value = "运行花费时间")
    private Integer spendTime;

}
