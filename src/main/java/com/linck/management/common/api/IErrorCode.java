package com.linck.management.common.api;

/**
 * @author: linck
 * 封装API的错误码
 */
public interface IErrorCode {

    /**
     * 获取错误码
     * @return
     */
    int getCode();

    /**
     * 获取message
     * @return
     */
    String getMessage();
}
