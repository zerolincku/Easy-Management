package com.linck.management.common.exception;

import com.linck.management.common.api.ResultCodeEnum;

/**
 * 自定义运行时异常
 * @author linck
 **/
public class BizException extends RuntimeException {

    /**
     * 状态码
     */
    private final Integer code;

    public BizException(String message) {
        super(message);
        this.code = ResultCodeEnum.FAILED.getCode();
    }

    /**
     * 自定义异常
     *
     * @param resultCodeEnum 返回枚举对象
     */
    public BizException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    /**
     * @param resultCodeEnum 返回枚举对象
     * @param message 错误信息
     */
    public BizException(ResultCodeEnum resultCodeEnum, String message) {
        super(message);
        this.code = resultCodeEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

}
