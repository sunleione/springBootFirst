package cn.sunlei.springmybatis.common.base;


/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 */
public interface BaseErrorEnum {

    Integer FORBIDDEN = 403;
    Integer UNAUTHORIZED = 401;
    Integer BAD_REQUEST = 400;
    Integer INTERNAL_ERROR = 500;

    Integer getErrorStatus();

    String getCodeId();

    String getMessage();
}
