package org.example.oauth2.userdetails;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.example.common.model.PlatformUserDetails;
import org.example.common.model.ResourceAuthority;
import org.example.oauth2.dao.UserDao;
import org.example.oauth2.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/18 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Slf4j
//@Service
public class PlatformUserDetailsServiceImpl extends ServiceImpl<UserDao, User> implements PlatformUserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        PlatformUserDetails userDetails = new PlatformUserDetails();
//  TODO: Children 类如何转换为 Wrapper<T>
//   User user2 = getOne(new QueryWrapper<>().eq("user_code", username));
        User user = getOne(Wrappers.<User>lambdaQuery().eq(User::getUserCode, username));
        if (user == null) {
            log.debug("没有找到用户名为：{} 的用户信息", username);
            throw new UsernameNotFoundException("不存在用户名为：" + username + "的用户");
        }
        userDetails.setUsername(username);
        userDetails.setCompanyName("天空行者");
        userDetails.setDepartmentName("动力研究部");
        userDetails.setPassword(user.getPassword());

        HashMap<String, String> authorities2 = new HashMap<>();
        authorities2.put("/api/oom/sys-user", "GET");
        authorities2.put("/api/oom/sys-user", "PUT");
        authorities2.put("/api/oom/sys-user/{id}", "DELETE");

        List<ResourceAuthority> authorities = new ArrayList<>();
        authorities.add(new ResourceAuthority("/api/oom/sys-user", "GET", "query_user"));
        authorities.add(new ResourceAuthority("/api/oom/sys-user", "PUT", "update_user"));
        authorities.add(new ResourceAuthority("/api/oom/sys-user/{id}", "DELETE", "delete_user"));

        userDetails.setAuthorities2(authorities2);
        userDetails.setAuthorities(authorities);

        return userDetails;
    }
}
