package cn.sunlei.springmybatis.controller;

import cn.sunlei.springmybatis.dao.UserMapper;
import cn.sunlei.springmybatis.entity.User;
import cn.sunlei.springmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/info")
    public User getUser(){
        User userById = userService.getUserById(140295);
            return userById;
    }
}
