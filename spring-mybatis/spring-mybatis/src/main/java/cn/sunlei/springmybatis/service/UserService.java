package cn.sunlei.springmybatis.service;

import cn.sunlei.springmybatis.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserService {
    User getUserById(Integer id);
}

