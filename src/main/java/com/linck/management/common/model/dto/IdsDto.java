package com.linck.management.common.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 主键集合
 *
 * @author linck
 * @date 2023-04-11
 */
@Data
public class IdsDto {

    /**
     * 主键id集合
     */
    @NotNull(message = "ids不能为空")
    @NotEmpty(message = "ids不能为空")
    private List<Long> ids;
}
