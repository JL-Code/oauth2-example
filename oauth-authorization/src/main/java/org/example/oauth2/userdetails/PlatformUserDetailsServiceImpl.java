package org.example.oauth2.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/18 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Service
public class PlatformUserDetailsServiceImpl implements PlatformUserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PlatformUserDetails user = new PlatformUserDetails();
        user.setUsername(username);
        user.setPassword("123");
        return user;
    }
}
