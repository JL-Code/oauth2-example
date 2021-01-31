package oauth2.example.authorization.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import oauth2.example.authorization.entity.User;
import oauth2.example.authorization.repository.UserRepository;
import oauth2.example.authorization.security.model.ResourceGrantedAuthority;
import oauth2.example.authorization.security.model.UserIdentity;
import oauth2.example.authorization.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * <p>创建时间: 2021/1/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Service
@Primary
public class UserServiceImpl extends ServiceImpl<UserRepository, User> implements UserService, UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = super.getBaseMapper().selectOne(
                Wrappers.<User>lambdaQuery().eq(User::getUsername, username).or().eq(User::getPhoneNumber, username)
        );

        if (user == null) {
            throw new UsernameNotFoundException(String.format("未找到 [%s] 相关的用户信息", username));
        }

        UserIdentity identity = new UserIdentity();

        BeanUtils.copyProperties(user, identity);

        identity.setAccountNonExpired(true);
        identity.setAccountNonLocked(true);
        identity.setEnabled(true);
        identity.setCredentialsNonExpired(true);

        identity.setAuthorities(Arrays.asList(new ResourceGrantedAuthority("ROLE_ADMIN")));

        return identity;
    }
}
