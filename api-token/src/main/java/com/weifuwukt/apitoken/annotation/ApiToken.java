package com.weifuwukt.apitoken.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * api幂等问题注解
 * @author 杨郑兴
 * @Date 2019/1/10 17:58
 * @官网 www.weifuwukt.com
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiToken {
    String type();
}
