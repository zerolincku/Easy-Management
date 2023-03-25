package com.linck.management.common.model.dto;

import com.linck.management.common.model.enums.StatusEnum;
import lombok.Data;

/**
 * @author linck
 **/
@Data
public class StatusDto {

    /**
     * 状态 1-启用 0-禁用
     */
    private StatusEnum status;
}
