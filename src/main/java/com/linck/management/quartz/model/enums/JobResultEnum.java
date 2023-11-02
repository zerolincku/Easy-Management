package com.linck.management.quartz.model.enums;

import com.linck.management.common.model.enums.NameValueInterface;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@AllArgsConstructor
public enum JobResultEnum implements NameValueInterface {

    SUCCESS(1, "成功"),
    FAILURE(0, "失败");

    private final Integer value;

    private final String name;
}
