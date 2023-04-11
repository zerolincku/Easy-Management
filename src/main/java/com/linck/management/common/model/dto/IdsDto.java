package com.linck.management.common.model.dto;

import lombok.Data;

import java.util.List;

/**
 * 主键集合
 *
 * @author linck
 * @date 2023-04-11
 */
@Data
public class IdsDto {

    private List<Long> ids;
}
