package cn.sunlei.springmybatis.city.controller;

import cn.sunlei.springmybatis.city.CityExceptionEnum;
import cn.sunlei.springmybatis.city.CityValidError;
import cn.sunlei.springmybatis.city.entity.City;
import cn.sunlei.springmybatis.city.service.impl.CityServiceImpl;
import cn.sunlei.springmybatis.city.vo.CityCondition;
import cn.sunlei.springmybatis.exception.SMException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Api(value = "city moudle")
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @ApiOperation(value = "get")
    @PostMapping("/list")
    public List<City> getCity(@RequestBody @Valid CityCondition cityCondition) {
        return cityService.getCityById(cityCondition);
    }

}
