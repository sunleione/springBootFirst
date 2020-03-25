package cn.sunlei.springmybatis.service.impl;

import cn.sunlei.springmybatis.dao.UserMapper;
import cn.sunlei.springmybatis.entity.User;
import cn.sunlei.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        List<User> userById =userMapper.getUserById(id);
        return userById.get(0);
    }
}
