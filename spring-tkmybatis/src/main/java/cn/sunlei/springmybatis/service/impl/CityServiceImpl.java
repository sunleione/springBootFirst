package cn.sunlei.springmybatis.service.impl;

import cn.sunlei.springmybatis.common.BaseService;
import cn.sunlei.springmybatis.common.BaseServiceImpl;
import cn.sunlei.springmybatis.common.MyMapper;
import cn.sunlei.springmybatis.dao.CityMapper;
import cn.sunlei.springmybatis.entity.City;
import cn.sunlei.springmybatis.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl extends BaseServiceImpl<City,Integer> implements CityService{

    @Autowired
    private CityMapper cityMapper;

    @Override
    protected MyMapper<City> getDao() {
        return cityMapper;
    }
}
