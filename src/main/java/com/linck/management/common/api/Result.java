package com.linck.management.common.api;

import lombok.Data;

/**
 * 通用返回对象
 * @author linck
 */
@Data
public class Result<T> {

    private long code;
    private String message;


    protected Result() {
    }

    protected Result(long code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 成功返回结果
     */
    public static Result success() {
        return new Result(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage());
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static  Result success(Object data) {
        return new ResultAndData(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static  Result success(Object data, String message) {
        return new ResultAndData(ResultCodeEnum.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static  Result failed(IErrorCode errorCode) {
        return new Result(errorCode.getCode(), errorCode.getMessage());
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static  Result failed(String message) {
        return new Result(ResultCodeEnum.FAILED.getCode(), message);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static  Result validateFailed(String message) {
        return new Result(ResultCodeEnum.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 未登录返回结果
     */
    public static Result unauthorized() {
        return new Result(ResultCodeEnum.UNAUTHORIZED.getCode(), ResultCodeEnum.UNAUTHORIZED.getMessage());
    }

    /**
     * 未授权返回结果
     */
    public static Result forbidden() {
        return new Result(ResultCodeEnum.FORBIDDEN.getCode(), ResultCodeEnum.FORBIDDEN.getMessage());
    }

}
