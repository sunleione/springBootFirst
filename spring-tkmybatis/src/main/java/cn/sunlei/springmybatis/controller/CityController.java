package cn.sunlei.springmybatis.controller;

import cn.sunlei.springmybatis.entity.City;
import cn.sunlei.springmybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityService cityService;

    @RequestMapping("/list")
    public List<City> getCity(){
        return cityService.getAll();
    }

}
