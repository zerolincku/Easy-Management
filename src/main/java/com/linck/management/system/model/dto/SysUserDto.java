package com.linck.management.system.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author linck
 **/
@Data
public class SysUserDto {

    /**
     * 账号
     */
    @NotBlank(message = "账户不能为空")
    @Length(min = 4, max = 32, message = "账户长度需要4-32个字符")
    private String account;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 4, max = 32, message = "密码长度需要4-32个字符")
    private String password;

    /**
     * 验证码-暂未使用
     */
    // @NotBlank(message = "验证码不能为空")
    private String verificationCode;

}
