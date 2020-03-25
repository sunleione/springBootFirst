package cn.sunlei.springmybatis.controller;


import cn.sunlei.springmybatis.entity.User;
import cn.sunlei.springmybatis.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 932840053@qq.com
 * @since 2020-03-25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/one")
    public List<User> getUser(){
        IPage<User> userPage = new Page<>(2, 2);//参数一是当前页，参数二是每页个数
        IPage<User> page = userService.page(userPage, null);
        return page.getRecords();
    }

}
