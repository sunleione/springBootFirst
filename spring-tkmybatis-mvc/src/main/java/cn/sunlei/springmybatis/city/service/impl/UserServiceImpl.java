package cn.sunlei.springmybatis.city.service.impl;

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
