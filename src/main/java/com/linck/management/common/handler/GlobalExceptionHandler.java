package com.linck.management.common.handler;

import com.linck.management.common.api.CommonResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.StringJoiner;

/**
 * @program: MyManagement
 * @description
 * @author: Linck
 * @create: 2020-08-09 19:46
 **/
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Post参数验证失败
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResult ethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        // 获取所有的错误
        // List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        // 获取错误提示
        // System.out.println(fieldErrors.get(0).getDefaultMessage());
        // 获取错误字段
        // System.out.println(fieldErrors.get(0).getField());

        // 将所有的错误提示使用";"拼接起来并返回
        StringJoiner sj = new StringJoiner(";");
        e.getBindingResult().getFieldErrors().forEach(x -> sj.add(x.getDefaultMessage()));

        return CommonResult.failed(sj.toString());
    }

    /**
     * get参数验证失败,会抛出一个ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public CommonResult constraintViolationExceptionHandler(ConstraintViolationException e) {
        StringJoiner sj = new StringJoiner(";");
        e.getConstraintViolations().forEach(x -> sj.add(x.getMessage()));

        return CommonResult.failed(sj.toString());
    }

}
