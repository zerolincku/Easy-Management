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

    /**
     * 方法名称
     */
    private final String method;


    /**
     * 自定义异常
     *
     * @param resultCodeEnum 返回枚举对象
     * @param method         方法
     */
    public BizException(ResultCodeEnum resultCodeEnum, String method) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
        this.method = method;
    }

    /**
     * @param code    状态码
     * @param message 错误信息
     * @param method  方法
     */
    public BizException(Integer code, String message, String method) {
        super(message);
        this.code = code;
        this.method = method;
    }

    public Integer getCode() {
        return code;
    }

    public String getMethod() {
        return method;
    }
}
