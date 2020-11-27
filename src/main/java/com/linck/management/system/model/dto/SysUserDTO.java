package com.linck.management.system.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @program: MyManagement
 * @description
 * @author: linck
 * @create: 2020-08-09 17:45
 **/
@Data
public class SysUserDTO {

    @ApiModelProperty(value = "账号", required = true)
    @NotBlank(message = "账户不能为空")
    @Length(min = 4, max = 32, message = "账户长度需要4-32个字符")
    private String account;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 32, message = "密码长度需要4-32个字符")
    private String password;

    @ApiModelProperty(value = "验证码-暂未使用", required = false)
    // @NotBlank(message = "验证码不能为空")
    private String verificationCode;

}
