package com.linck.management.common.handler;

import com.linck.management.common.api.Result;
import com.linck.management.common.api.ResultCodeEnum;
import com.linck.management.common.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Objects;
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
        return Result.failed(Objects.requireNonNull(ResultCodeEnum.getByCode(e.getCode())));
    }

    /**
     * 捕获异常返回详细异常信息
     */
    @ExceptionHandler(Exception.class)
    public Result<String> exceptionHandler(Exception e) {
        log.error("捕获异常", e);
        return Result.failed(e.getMessage());
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
     * get参数验证异常,会抛出一个ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> constraintViolationExceptionHandler(ConstraintViolationException e) {
        StringJoiner sj = new StringJoiner("，");
        e.getConstraintViolations().forEach(x -> sj.add(x.getMessage()));

        return Result.validateFailed(sj.toString());
    }

}
