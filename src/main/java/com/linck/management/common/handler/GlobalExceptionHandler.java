package com.linck.management.common.handler;

import com.linck.management.common.api.Result;
import com.linck.management.common.api.ResultCodeEnum;
import com.linck.management.common.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * @program: MyManagement
 * @description 全局异常捕获
 * @author: linck
 * @create: 2020-08-09 19:46
 **/
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获自定义异常
     */
    @ExceptionHandler(value = CustomException.class)
    public Result processException(CustomException e) {
        log.error("位置:{} -> 错误信息:{}", e.getMethod(), e.getLocalizedMessage());
        return Result.failed(Objects.requireNonNull(ResultCodeEnum.getByCode(e.getCode())));
    }

    /**
     * Post参数验证异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result ethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 将所有的错误提示使用"，"拼接起来并返回
        StringJoiner sj = new StringJoiner("，");
        e.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));
        return Result.validateFailed(sj.toString());
    }

    /**
     * get参数验证异常,会抛出一个ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {
        StringJoiner sj = new StringJoiner("，");
        e.getConstraintViolations().forEach(x -> sj.add(x.getMessage()));

        return Result.validateFailed(sj.toString());
    }

    /**
     * 捕获异常返回详细异常信息
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e) {
        e.printStackTrace();
        return Result.failed(e.getMessage());
    }

}
