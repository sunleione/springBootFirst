package cn.sunlei.springmybatis.service.impl;

import cn.sunlei.springmybatis.dao.UserMapper;
import cn.sunlei.springmybatis.entity.User;
import cn.sunlei.springmybatis.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 932840053@qq.com
 * @since 2020-03-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
