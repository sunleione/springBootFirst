package cn.sunlei.springmybatis.exception;

import cn.sunlei.springmybatis.common.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 */
@Slf4j
@RestControllerAdvice("cn.sunlei.springmybatis")
public class GlobalExceptionHandler {

    /**
     *   自定义全局异常
     * @param
     * @return
     */
    @ExceptionHandler(SMException.class)
    @ResponseBody
    public BaseResponse accessDeniedException(SMException e, HttpServletResponse response) {
        BaseResponse baseResponse = new BaseResponse();
        if(Objects.isNull(e.getErrorInfo())){
            BaseErrorEnum errorInfo = e.getErrorInfo();
            baseResponse.setCode(errorInfo.getCodeId());
            baseResponse.setMessage(errorInfo.getMessage());
            response.setStatus(errorInfo.getErrorStatus());
        }
        return baseResponse;
    }

    /**
     *  validException
     * @param
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public BaseResponse argumentNotValidException(MethodArgumentNotValidException e) {
        log.info("exception message ex={}", e.getMessage(), e);
        return new BaseResponse();
    }

    /**
     * GlobalException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse exception(Exception e) {
        log.info("exception message ex={}", e.getMessage(), e);
        return new BaseResponse();
    }


}
