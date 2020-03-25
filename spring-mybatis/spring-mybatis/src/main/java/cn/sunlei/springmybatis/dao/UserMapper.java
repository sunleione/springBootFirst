package cn.sunlei.springmybatis.dao;

import cn.sunlei.springmybatis.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    List<User> getUserById(@Param("id") Integer id);
}
