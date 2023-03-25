package com.linck.management.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author linck
 **/
@Data
public class RolePermissionDto {

    /**
     * 角色id
     */
    @NotNull(message = "角色id不能为空")
    private Long roleId;

    /**
     * 权限id集合
     */
    @NotNull(message = "权限集合不能为NULL")
    private List<Long> permissionIdList;

}
