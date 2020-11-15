package com.linck.management.system.model.dto;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: management
 * @description
 * @author: linck
 * @create: 2020-11-13 23:15
 **/
public class RolePermissionDTO {

    @ApiModelProperty("角色id")
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    @NotNull(message = "权限集合不能为NULL")
    @ApiModelProperty("权限id集合")
    private List<Long> permissionIdList;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public List<Long> getPermissionIdList() {
        return permissionIdList;
    }

    public void setPermissionIdList(List<Long> permissionIdList) {
        this.permissionIdList = permissionIdList;
    }
}
