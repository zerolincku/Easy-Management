package com.linck.management.common.api;

/**
 * 封装API的错误码
 * @author linck
 */
public interface IErrorCode {

    /**
     * 获取错误码
     */
    int getCode();

    /**
     * 获取message
     */
    String getMessage();
}
