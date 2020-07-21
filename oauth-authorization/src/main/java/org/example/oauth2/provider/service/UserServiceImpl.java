package org.example.oauth2.provider.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.oauth2.dao.UserDao;
import org.example.oauth2.entity.User;
import org.springframework.stereotype.Service;

/**
 * 用户服务，提供各种方式查询、手机号、邮箱、OpenID 等等
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Override
    public User findUserByPhoneNumber(String phone) {
        // Wrappers.<User>lambdaQuery().eq(User::getUserCode, username)
        User loadedUser = getOne(Wrappers.<User>lambdaQuery().eq(User::getPhoneNumber, phone));
        return loadedUser;
    }
}
