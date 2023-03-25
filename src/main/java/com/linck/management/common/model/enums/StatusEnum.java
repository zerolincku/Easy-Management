package com.linck.management.common.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 通用状态枚举
 * @author linck
 **/
@Slf4j
@Getter
@AllArgsConstructor
public enum StatusEnum implements NameValueInterface {

    ENABLE(1, "启用"),
    DISABLE(0, "禁用"),
    ;

    @JsonValue
    private final Integer value;

    private final String name;
}
