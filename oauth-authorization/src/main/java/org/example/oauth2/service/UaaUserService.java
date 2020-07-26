package org.example.oauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.oauth2.entity.UaaUser;

public interface UaaUserService extends IService<UaaUser> {
    UaaUser findUserByPhoneNumber(String phone);
    UaaUser findUserByCorpWeChatUserId(String userId);
    UaaUser findUserByUsername(String username);
    UaaUser findUserByDingtalkUserId(String userId);
}
