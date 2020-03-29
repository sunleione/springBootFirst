package cn.sunlei.springmybatis.city.service;

import cn.sunlei.springmybatis.city.vo.CityCondition;
import cn.sunlei.springmybatis.common.base.BaseService;
import cn.sunlei.springmybatis.city.entity.City;

import java.util.List;

public interface CityService extends BaseService<City,Integer> {

    List<City> getCityById(CityCondition condition);
}
