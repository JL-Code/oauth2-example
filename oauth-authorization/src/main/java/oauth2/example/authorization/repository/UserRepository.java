package oauth2.example.authorization.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oauth2.example.authorization.entity.User;
import org.springframework.stereotype.Repository;

/**
 * <p>创建时间: 2021/1/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Repository
public interface UserRepository extends BaseMapper<User> {

}
