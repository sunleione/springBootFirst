package cn.sunlei.springmybatis.common;

import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface BaseService<T, Id> {

    int save(T t);

    int delete(T t);

    int deleteById(Id id);

    int deleteByExample(Object object);

    boolean existsWithPrimaryKey(Object o);

    int update(T t);

    int updateByExampleSelective(T t, Object o);

    T getById(Id id);

    List<T> getByIds(List<Id> ids);

    List<T> getAll();

    int saveAll(List<T> list);

    List<T> searchByExample(Object o);

    List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds);

    List<T> selectByRowBounds(T t, RowBounds rowBounds);
}
