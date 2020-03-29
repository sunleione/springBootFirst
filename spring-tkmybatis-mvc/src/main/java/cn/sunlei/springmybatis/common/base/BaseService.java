package cn.sunlei.springmybatis.common.base;

import org.apache.ibatis.session.RowBounds;

import java.io.Serializable;
import java.util.List;

/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 */

public interface BaseService<T,Id> {

    int save(T t);

    int delete(T t);

    int deleteById(Id id);

    int deleteByIds(List<Id> ids);

    int deleteByExample(Object object);

    boolean existsWithPrimaryKey(Object o);

    int update(T t);

    int updateByExampleSelective(T t, Object o);

    T getById(Id id);

    List<T> getAll();

    int saveAll(List<T> list);

    List<T> searchByExample(Object o);

    List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds);

    List<T> selectByRowBounds(T t, RowBounds rowBounds);
}
