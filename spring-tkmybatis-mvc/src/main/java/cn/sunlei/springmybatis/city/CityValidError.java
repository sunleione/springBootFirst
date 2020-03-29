package cn.sunlei.springmybatis.city;

import cn.sunlei.springmybatis.annotation.HttpCode;
import cn.sunlei.springmybatis.annotation.ValidError;

/**
 * @Date 2020/3/26 16:08
 * @Created by sunlei
 */
@ValidError
public class CityValidError {

    @HttpCode(codeId = "city_valid_001",errorStatus = 400)
    public static final String CITYID_NOT_EXIST = "city id not exist";

}
