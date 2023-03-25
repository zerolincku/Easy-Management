package com.linck.management.system.model.dto;

import com.linck.management.common.model.dto.PageDto;
import com.linck.management.common.model.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author linck
 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserSearchDto extends PageDto {

    /**
     * 账号
     */
    private String account;

    /**
     * 状态 1-启用 0-禁用
     */
    private StatusEnum status;

}
