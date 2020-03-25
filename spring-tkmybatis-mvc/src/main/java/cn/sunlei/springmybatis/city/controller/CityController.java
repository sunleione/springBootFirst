package cn.sunlei.springmybatis.city.controller;

import cn.sunlei.springmybatis.city.entity.City;
import cn.sunlei.springmybatis.city.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityServiceImpl cityService;

    @RequestMapping("/list")
    public List<City> getCity() {
        return cityService.getAll();
    }

}
