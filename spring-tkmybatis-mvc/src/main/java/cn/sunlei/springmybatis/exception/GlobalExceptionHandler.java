package cn.sunlei.springmybatis.exception;

import cn.sunlei.springmybatis.annotation.HttpCode;
import cn.sunlei.springmybatis.common.base.BaseErrorEnum;
import cn.sunlei.springmybatis.common.base.BaseResponse;
import cn.sunlei.springmybatis.valid.ValidErrorInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 */
@Slf4j
@RestControllerAdvice("cn.sunlei.springmybatis")
public class GlobalExceptionHandler {

    /**
     * 自定义全局异常
     *
     * @param
     * @return
     */
    @ExceptionHandler(SMException.class)
    public BaseResponse accessDeniedException(SMException e, HttpServletResponse response) {
        log.error("errorInfo {},message {},e {}", e.getErrorInfo().toString(), e.getMessage(), e.getCause());
        BaseResponse baseResponse = new BaseResponse();
        if (!Objects.isNull(e.getErrorInfo())) {
            BaseErrorEnum errorInfo = e.getErrorInfo();
            baseResponse.setCode(errorInfo.getCodeId());
            baseResponse.setMessage(errorInfo.getMessage());
            response.setStatus(errorInfo.getErrorStatus());
        } else if (!StringUtils.isEmpty(e.getMessage())) {
            baseResponse.setCode(ExptionResponseEnum.SMEXCEPTION_MESSAGE.codeId);
            baseResponse.setMessage(e.getMessage());
            response.setStatus(ExptionResponseEnum.SMEXCEPTION_MESSAGE.errorStatus);
        } else {
            baseResponse.setCode(ExptionResponseEnum.SMEXCEPTION_OTHER.getCodeId());
            baseResponse.setMessage(ExptionResponseEnum.SMEXCEPTION_OTHER.getMessage());
            response.setStatus(ExptionResponseEnum.SMEXCEPTION_OTHER.getErrorStatus());
        }
        return baseResponse;
    }

    /**
     * validException
     *
     * @param
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResponse argumentNotValidException(MethodArgumentNotValidException e, HttpServletResponse response) {
        log.error("MethodArgumentNotValidException,{}", e);
        BindingResult bindingResult = e.getBindingResult();
        String defaultMessage = bindingResult.getFieldError().getDefaultMessage();
        BaseResponse baseResponse = new BaseResponse();
        if (StringUtils.isEmpty(defaultMessage)) {
            baseResponse.setMessage(ExptionResponseEnum.UNKNOWN_VALID_MESSAGE.getMessage());
            baseResponse.setCode(ExptionResponseEnum.UNKNOWN_VALID_MESSAGE.getCodeId());
            response.setStatus(ExptionResponseEnum.UNKNOWN_VALID_MESSAGE.getErrorStatus());
            return baseResponse;

        }
        Map<Field, Class<?>> allValidErrorMap = ValidErrorInstance.getAllValidErrorMap();
        Set<Field> fields = allValidErrorMap.keySet();
        for (Field field : fields) {
            Class<?> aClass = allValidErrorMap.get(field);
            try {
                Field field1 = aClass.getField(field.getName());
                Object o = field1.get(aClass.newInstance());
                if (o instanceof String && defaultMessage.equalsIgnoreCase((String) o)) {
                    HttpCode annotation = field.getAnnotation(HttpCode.class);
                    baseResponse.setMessage(defaultMessage);
                    baseResponse.setCode(annotation.codeId());
                    response.setStatus(annotation.errorStatus());
                    return baseResponse;
                } else {
                    log.error("field type error,field name {},class name {}", field.getName(), aClass.getName());
                }
            } catch (NoSuchFieldException | InstantiationException | IllegalAccessException e1) {
                log.error("field error,{}", e);
            }

        }

        baseResponse.setMessage(ExptionResponseEnum.UNKNOWN_VALID_MESSAGE.getMessage());
        baseResponse.setCode(ExptionResponseEnum.UNKNOWN_VALID_MESSAGE.getCodeId());
        response.setStatus(ExptionResponseEnum.UNKNOWN_VALID_MESSAGE.getErrorStatus());
        return baseResponse;
    }

    /**
     * GlobalException
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public BaseResponse exception(Exception e, HttpServletResponse response) {
        log.error("Exception,{}", e);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(ExptionResponseEnum.SYSTEM_EXCEPTION.getCodeId());
        baseResponse.setMessage(e.getMessage());
        response.setStatus(ExptionResponseEnum.SYSTEM_EXCEPTION.getErrorStatus());
        return baseResponse;
    }


    /**
     *  静态内部类和内部类的区别: 静态内部类可以使用 外部类.静态内部类的方式
     *                            而内部类在外面用的时候： new 外部类().内部类
     *             因此不需要外部引用的内部类就创建为非静态的
     */
    private enum ExptionResponseEnum implements BaseErrorEnum {
        UNKNOWN_VALID_MESSAGE("globalexceptionhandler_001", "valid default message error", INTERNAL_ERROR),
        SMEXCEPTION_OTHER("globalexceptionhandler_002", "SMException other info ", INTERNAL_ERROR),
        SYSTEM_EXCEPTION("globalexceptionhandler_003", "system exception", INTERNAL_ERROR),
        SMEXCEPTION_MESSAGE("globalexceptionhandler_004", "SMException message info ", INTERNAL_ERROR);


        private String codeId;
        private String message;
        private Integer errorStatus;

        private ExptionResponseEnum(String codeId, String message, Integer errorStatus) {
            this.codeId = codeId;
            this.message = message;
            this.errorStatus = errorStatus;
        }

        @Override
        public Integer getErrorStatus() {
            return this.errorStatus;
        }

        @Override
        public String getCodeId() {
            return this.codeId;
        }

        @Override
        public String getMessage() {
            return this.message;
        }
    }


}
