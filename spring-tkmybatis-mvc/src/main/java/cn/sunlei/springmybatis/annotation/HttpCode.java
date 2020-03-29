package cn.sunlei.springmybatis.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Date 2020/3/25 17:40
 * @Created by sunlei
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface HttpCode {
    String codeId() default "";
    int errorStatus() default 400;
}
