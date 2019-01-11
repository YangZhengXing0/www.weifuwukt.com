package com.weifuwukt.accesstoken.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 * @author 杨郑兴
 * @Date 2019/1/11 13:10
 * @官网 www.weifuwukt.com
 */
@RestControllerAdvice
public class AccessTokenException {

    @ExceptionHandler(RuntimeException.class)
    public String AccessException(Exception e){
        return e.getMessage();
    }
}
