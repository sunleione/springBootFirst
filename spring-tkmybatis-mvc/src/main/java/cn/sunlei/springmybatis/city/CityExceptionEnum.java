package cn.sunlei.springmybatis.city;


import cn.sunlei.springmybatis.common.base.BaseErrorEnum;

/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 */
public enum CityExceptionEnum implements BaseErrorEnum {


    CITY_NOT_EXIST("city_0001","city not exist",BAD_REQUEST);

    private String CodeId;
    private String message;
    private Integer errorStatus;

    private CityExceptionEnum(String codeId,String message,Integer errorStatus){
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
