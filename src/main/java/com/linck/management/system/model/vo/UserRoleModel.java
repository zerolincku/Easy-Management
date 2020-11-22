package com.linck.management.system.model.vo;

import io.swagger.annotations.ApiModelProperty;

/**
 * @program: management
 * @description 用户角色
 * @author: linck
 * @create: 2020-11-22 15:08
 **/
public class UserRoleModel {

    public UserRoleModel() {
    }

    public UserRoleModel(Long id, String name, Boolean checked) {
        this.id = id;
        this.name = name;
        this.checked = checked;
    }

    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("是否选中")
    private Boolean checked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
