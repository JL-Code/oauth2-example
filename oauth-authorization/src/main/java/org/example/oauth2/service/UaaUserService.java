package org.example.oauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.oauth2.entity.UaaUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UaaUserService extends IService<UaaUser>, UserDetailsService {
    UaaUser findUserByPhoneNumber(String phone);
    UaaUser findUserByCorpWeChatUserId(String userId);
    UaaUser findUserByUsername(String username);
    UaaUser findUserByDingtalkUserId(String userId);
}
