package com.linck.management.system.model.dto;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @program: MyManagement
 * @description
 * @author: linck
 * @create: 2020-08-09 17:45
 **/
public class LoginUserDTO {

    @ApiModelProperty(value = "账号", required = true)
    @NotBlank(message = "账户不能为空")
    @Length(min = 5, max = 32, message = "账户长度需要5-32个字符")
    private String account;

    @ApiModelProperty(value = "密码", required = true)
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 32, message = "密码长度需要6-32个字符")
    private String password;

    @ApiModelProperty(value = "验证码-暂未使用", required = false)
    // @NotBlank(message = "验证码不能为空")
    private String verificationCode;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    @Override
    public String toString() {
        return "LoginUserDTO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                '}';
    }
}
