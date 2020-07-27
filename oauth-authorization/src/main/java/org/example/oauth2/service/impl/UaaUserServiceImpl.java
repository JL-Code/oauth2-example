package org.example.oauth2.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.oauth2.dao.UaaUserDao;
import org.example.oauth2.entity.UaaUser;
import org.example.oauth2.service.UaaUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 用户服务，提供各种方式查询、手机号、邮箱、企业微信用户ID、钉钉用户ID 等等
 */
@Service
public class UaaUserServiceImpl extends ServiceImpl<UaaUserDao, UaaUser> implements UaaUserService {

    @Override
    public UaaUser findUserByPhoneNumber(String phone) {
        UaaUser loadedUser = getOne(Wrappers.<UaaUser>lambdaQuery().eq(UaaUser::getPhoneNumber, phone));
        return loadedUser;
    }

    @Override
    public UaaUser findUserByCorpWeChatUserId(String userId) {
        UaaUser loadedUser = getOne(Wrappers.<UaaUser>lambdaQuery().eq(UaaUser::getCorpWeChatUserId, userId));
        return loadedUser;
    }

    @Override
    public UaaUser findUserByUsername(String username) {
        UaaUser loadedUser = getOne(Wrappers.<UaaUser>lambdaQuery().eq(UaaUser::getUserCode, username));
        return loadedUser;
    }

    @Override
    public UaaUser findUserByDingtalkUserId(String userId) {
        UaaUser loadedUser = getOne(Wrappers.<UaaUser>lambdaQuery().eq(UaaUser::getDingtalkUserId, userId));
        return loadedUser;
    }
}
