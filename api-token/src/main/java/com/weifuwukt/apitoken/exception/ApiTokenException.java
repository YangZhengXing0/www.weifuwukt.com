package com.weifuwukt.apitoken.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局捕获异常
 * @author 杨郑兴
 * @Date 2019/1/10 21:21
 * @官网 www.weifuwukt.com
 */
@RestControllerAdvice
public class ApiTokenException {
    @ExceptionHandler(RuntimeException.class)
    public String ApiTokenException(Exception e){
        return e.getMessage();
    }
}
