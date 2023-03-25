package com.linck.management.common.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author linck
 **/
@Data
public class IdDto {

    /**
     * 主键id
     */
    @NotNull(message = "id不能为空")
    private Long id;

}
