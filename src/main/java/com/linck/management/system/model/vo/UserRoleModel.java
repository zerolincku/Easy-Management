package com.linck.management.system.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 用户角色
 * @author linck
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRoleModel {

    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 是否选中
     */
    private Boolean checked;

}
