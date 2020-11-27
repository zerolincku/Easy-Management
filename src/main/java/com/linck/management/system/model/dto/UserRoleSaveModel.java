package com.linck.management.system.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @program: MyManagement
 * @description
 * @author: linck
 * @create: 2020-11-23
 **/
@Data
public class UserRoleSaveModel {

    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    private Long userId;

    @ApiModelProperty("角色id集合")
    @NotNull(message = "角色id集合不能为NULL")
    private List<Long> roleIdList;

}
