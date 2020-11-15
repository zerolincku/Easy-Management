package com.linck.management.system.model.dto;

import com.linck.management.common.model.Page;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: management
 * @description
 * @author: Linck
 * @create: 2020-11-15 20:57
 **/
public class SysUserSearchDTO extends Page {

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "状态 1-启用 0-禁用")
    private Integer state;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
