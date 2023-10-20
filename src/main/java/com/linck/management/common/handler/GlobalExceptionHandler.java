package com.linck.management.common.handler;

import com.linck.management.common.api.Result;
import com.linck.management.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.StringJoiner;

/**
 * 全局异常捕获
 * @author linck
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获自定义异常
     */
    @ExceptionHandler(value = BizException.class)
    public Result<String> processException(BizException e) {
        log.error("捕获自定义异常", e);
        return Result.failed(e.getMessage());
    }

    /**
     * 捕获异常返回详细异常信息
     */
    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error("捕获异常", e);
        return Result.failed("系统错误，错误时间：" + LocalDateTime.now());
    }

    /**
     * Post参数验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 将所有的错误提示使用"，"拼接起来并返回
        StringJoiner sj = new StringJoiner("，");
        e.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return Result.validateFailed(sj.toString());
    }

    /**
     * get参数验证异常,会抛出一个BindException
     */
    @ExceptionHandler(BindException.class)
    public Result<String> constraintViolationExceptionHandler(BindException e) {
        StringJoiner sj = new StringJoiner("，");
        e.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));

        return Result.validateFailed(sj.toString());
    }

}
