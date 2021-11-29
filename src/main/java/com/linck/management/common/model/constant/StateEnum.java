package com.linck.management.common.model.constant;

import com.fasterxml.jackson.annotation.JsonValue;
import com.linck.management.common.api.ResultCodeEnum;
import com.linck.management.common.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: MyManagement
 * @description 通用状态枚举
 * @author: linck
 * @create: 2020-08-09 17:20
 **/
@Slf4j
@Getter
@AllArgsConstructor
public enum StateEnum {

    /**
     * 启用
     */
    ENABLE(1, "启用"),
    /**
     * 禁用
     */
    DISABLE(0, "禁用");

    @JsonValue
    private Integer value;
    
    private String name;

    @SneakyThrows
    public static StateEnum getStateEnum(int value) {
        for (StateEnum stateEnum : StateEnum.values()) {
            if (stateEnum.value == value) {
                return stateEnum;
            }
        }
        log.warn("没有对应的 State，value: {}", value);
        throw new CustomException(ResultCodeEnum.NO_STATE_VALUE, "StateEnum.getStateEnum");
    }
}
