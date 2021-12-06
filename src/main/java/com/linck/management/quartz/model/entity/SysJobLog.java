package com.linck.management.quartz.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * Entity
 *
 * @author linck
 * @create 2021-11-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "SysJobLog对象", description = "")
public class SysJobLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "job的Id")
    private Long jobId;

    @ApiModelProperty(value = "描述")
    private String msg;

    @ApiModelProperty(value = "结果 1-成功 0-失败")
    private Integer result;

    @ApiModelProperty(value = "运行花费时间")
    private Integer spendTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

}