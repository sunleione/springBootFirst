package cn.sunlei.springmybatis.city.dao;

import cn.sunlei.springmybatis.common.base.MyMapper;
import cn.sunlei.springmybatis.city.entity.City;
import org.springframework.stereotype.Repository;


/**
 * 加@Repository  是为了防止注入的时候系统error  只起注释作用
 *
 * 加@Mapper 注解的作用 和 @MapperScan注解的作用相同 都是注入spring容器
 */

@Repository
public interface CityMapper extends MyMapper<City>{

}
