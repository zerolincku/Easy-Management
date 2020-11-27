package com.linck.management.system.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: management
 * @description 用户角色
 * @author: linck
 * @create: 2020-11-22 15:08
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleModel {

    private Long id;

    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("是否选中")
    private Boolean checked;

}
