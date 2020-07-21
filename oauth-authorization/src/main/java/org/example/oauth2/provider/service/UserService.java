package org.example.oauth2.provider.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.oauth2.entity.User;

public interface UserService extends IService<User> {
    User findUserByPhoneNumber(String phone);
}
