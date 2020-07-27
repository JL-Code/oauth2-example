package org.example.oauth2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.oauth2.entity.UaaUser;

/**
 * UAA 用户服务，提供检索用户的方法.
 */
public interface UaaUserService extends IService<UaaUser> {

    /**
     * 根据指定的手机号码，查找用户.
     *
     * @param phone 指定的手机号码
     * @return 一个从存储介质中查询到的用户信息
     */
    UaaUser findUserByPhoneNumber(String phone);

    /**
     * 根据指定的企业微信组织用户标识，查找用户.
     *
     * @param userId 企业微信组织中的用户标识
     * @return 一个从存储介质中查询到的用户信息
     */
    UaaUser findUserByCorpWeChatUserId(String userId);

    /**
     * 根据指定的用户标识，查找用户.
     *
     * @param username 用户名
     * @return 一个从存储介质中查询到的用户信息
     */
    UaaUser findUserByUsername(String username);

    /**
     * 根据指定的钉钉组织用户标识，查找用户.
     *  钉钉应用内部免登提供的标识为： userid
     *  钉钉扫码登录第三方网站提供的标识为：openid
     * 详情参考： https://ding-doc.dingtalk.com/doc#/serverapi2/vt6khw
     * @param userId 钉钉组织中的用户标识
     * @return 一个从存储介质中查询到的用户信息
     */
    UaaUser findUserByDingtalkUserId(String userId);
}
