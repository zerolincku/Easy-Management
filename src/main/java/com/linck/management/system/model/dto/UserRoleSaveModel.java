package com.linck.management.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author linck
 **/
@Data
public class UserRoleSaveModel {

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空")
    private Long userId;

    /**
     * 角色id集合
     */
    @NotNull(message = "角色id集合不能为NULL")
    private List<Long> roleIdList;

}
