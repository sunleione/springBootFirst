package cn.sunlei.springmybatis.common.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 */

public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
