package com.linck.management.system.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linck.management.common.handler.jackson.StateDeserializer;
import com.linck.management.common.model.constant.StateEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

/**
 * @author linck
 * @create 2020-11-04
 */
@Data
@ApiModel(value = "SysUser对象")
public class SysUser {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "账号")
    @NotBlank(message = "账户不能为空")
    @Length(min = 4, max = 32, message = "账户长度需要4-32个字符")
    private String account;

    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 32, message = "密码长度需要4-32个字符")
    private String pwd;

    @JsonDeserialize(using = StateDeserializer.class)
    @ApiModelProperty(value = "状态 1-启用 0-禁用")
    private StateEnum state;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

}
