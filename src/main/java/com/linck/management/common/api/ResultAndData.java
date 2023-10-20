package com.linck.management.common.api;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 包含 data 的返回体
 *
 * @author linck
 * @since 2023-10-20
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ResultAndData extends Result {

    private Object data;

    public ResultAndData(long code, String message, Object data) {
        super(code, message);
        this.data = data;
    }

}
