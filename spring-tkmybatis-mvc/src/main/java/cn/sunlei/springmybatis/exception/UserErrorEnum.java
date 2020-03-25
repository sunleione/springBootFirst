package cn.sunlei.springmybatis.exception;


/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 */
public enum UserErrorEnum implements BaseErrorEnum {


    USER_NOT_EXIST("user_0001","city not exist",BAD_REQUEST);

    private String CodeId;
    private String message;
    private Integer errorStatus;

    private UserErrorEnum(String codeId,String message,Integer errorStatus){
        this.CodeId=codeId;
        this.message=message;
        this.errorStatus=errorStatus;
    }


    @Override
    public Integer getErrorStatus() {
        return this.errorStatus;
    }

    @Override
    public String getCodeId() {
        return this.CodeId;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
