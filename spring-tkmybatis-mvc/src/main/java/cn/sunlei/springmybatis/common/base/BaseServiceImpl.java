package cn.sunlei.springmybatis.common.base;

import org.apache.ibatis.session.RowBounds;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;


/**
 * @Date 2020/3/25 17:38
 * @Created by sunlei
 *
 * 待研究 @Transactional的使用失效的场景
 */
public abstract class BaseServiceImpl<T extends BaseEntity, Id extends Serializable > implements BaseService<T, Id> {


    protected abstract MyMapper<T> getDao();

    @Override
    @Transactional
    public int save(T t) {
        return getDao().insertSelective(t);
    }

    @Override
    @Transactional
    public int saveAll(List<T> list) {
        int result = 0;
        for (T t : list) {
            result += getDao().insertSelective(t);
        }
        return result;
    }

    @Override
    @Transactional
    public int delete(T t) {
        return getDao().delete(t);
    }

    @Override
    @Transactional
    public int deleteById(Id id) {
        return getDao().deleteByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int deleteByIds(List<Id> ids) {
        if(CollectionUtils.isEmpty(ids)){
            return 0;
        }
        ids.stream().forEach(v->{
            getDao().deleteByPrimaryKey(v);
        });
        return ids.size();
    }

    @Override
    public int deleteByExample(Object object) {
        return getDao().deleteByExample(object);
    }

    @Override
    public boolean existsWithPrimaryKey(Object o) {
        return getDao().existsWithPrimaryKey(o);
    }

    @Override
    @Transactional
    public int update(T t) {
        return getDao().updateByPrimaryKeySelective(t);
    }

    @Override
    @Transactional
    public int updateByExampleSelective(T t, Object o) {
        return getDao().updateByExampleSelective(t, o);
    }

    @Override
    public T getById(Id id) {
        return getDao().selectByPrimaryKey(id);
    }

    @Override
    public List<T> getAll() {
        return getDao().selectAll();
    }

    @Override
    public List<T> searchByExample(Object o) {
        return getDao().selectByExample(o);
    }

    @Override
    public List<T> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        List<T> list = getDao().selectByExampleAndRowBounds(o, rowBounds);
        return list;
    }

    @Override
    public List<T> selectByRowBounds(T t, RowBounds rowBounds) {
        List<T> list = getDao().selectByRowBounds(t, rowBounds);
        return list;
    }


}
