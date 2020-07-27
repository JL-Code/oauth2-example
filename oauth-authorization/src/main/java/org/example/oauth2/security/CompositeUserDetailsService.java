package org.example.oauth2.security;

import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.example.common.model.UaaUserDetails;
import org.example.oauth2.security.authentication.Authenticator;
import org.example.oauth2.service.UaaUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>创建时间: 2020/7/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Slf4j
@Service
public class CompositeUserDetailsService implements UserDetailsService {

    @Autowired(required = false)
    private List<Authenticator> authenticators;
    @Autowired
    private UaaUserService uaaUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var loadedUser = uaaUserService.findUserByUsername(username);
        var userDetails = new UaaUserDetails();

        if (loadedUser == null) {
            log.debug("username:{} 的用户在存储介质中没有找到。", username);
            throw new UsernameNotFoundException("用户或密码错误");
        }

        BeanUtils.copyProperties(loadedUser, userDetails);

        return userDetails;
    }
}
