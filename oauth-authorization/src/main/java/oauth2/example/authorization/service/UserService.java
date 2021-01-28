package oauth2.example.authorization.service;

import com.baomidou.mybatisplus.extension.service.IService;
import oauth2.example.authorization.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>创建时间: 2021/1/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface UserService extends IService<User>, UserDetailsService {
}
