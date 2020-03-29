package cn.sunlei.springmybatis.city.vo;

import cn.sunlei.springmybatis.city.CityValidError;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Date 2020/3/26 16:14
 * @Created by sunlei
 */

@Data
@ApiModel(value = "city 查询条件")
public class CityCondition {

    @ApiModelProperty(value = "id")
    @NotNull(message = CityValidError.CITYID_NOT_EXIST)
    private Integer id;

}
