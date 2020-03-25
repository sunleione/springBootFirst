package cn.sunlei.mybatisplus.User.service.impl;

import cn.sunlei.mybatisplus.User.entity.User;
import cn.sunlei.mybatisplus.User.mapper.UserMapper;
import cn.sunlei.mybatisplus.User.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 932840053@qq.com
 * @since 2020-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
