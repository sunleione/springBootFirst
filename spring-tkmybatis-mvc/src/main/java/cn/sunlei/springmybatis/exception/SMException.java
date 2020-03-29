package cn.sunlei.springmybatis.exception;

import cn.sunlei.springmybatis.common.base.BaseErrorEnum;

/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 */
public class SMException extends RuntimeException {

    /**
     *  各模块自定义枚举根接口
     */
    private BaseErrorEnum errorInfo;


    public SMException(String message){
        super(message);
    }

    public SMException(BaseErrorEnum errorInfo){
        super();
        this.errorInfo=errorInfo;
    }

    public SMException(BaseErrorEnum errorInfo,String message){
            super(message);
            this.errorInfo=errorInfo;
    }

    public SMException(BaseErrorEnum errorInfo,String message,Throwable e){
        super(message,e);
        this.errorInfo=errorInfo;
    }

    public SMException(Exception e, BaseErrorEnum errorInfo) {
        super(e);
        this.errorInfo = errorInfo;
    }

    public BaseErrorEnum getErrorInfo() {
        return errorInfo;
    }
}
