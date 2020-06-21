package org.example.oauth2.userdetails;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.oauth2.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * <p>描述: 平台用户信息服务 </p>
 * <p>创建时间: 2020/6/18 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface PlatformUserDetailsService extends UserDetailsService, IService<User> {

}
