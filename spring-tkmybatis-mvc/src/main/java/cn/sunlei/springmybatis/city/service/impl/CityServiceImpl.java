package cn.sunlei.springmybatis.city.service.impl;

import cn.sunlei.springmybatis.city.dao.CityMapper;
import cn.sunlei.springmybatis.city.entity.City;
import cn.sunlei.springmybatis.city.service.CityService;
import cn.sunlei.springmybatis.common.base.BaseServiceImpl;
import cn.sunlei.springmybatis.common.base.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends BaseServiceImpl<City, Integer> implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    protected MyMapper<City> getDao() {
        return cityMapper;
    }
}
