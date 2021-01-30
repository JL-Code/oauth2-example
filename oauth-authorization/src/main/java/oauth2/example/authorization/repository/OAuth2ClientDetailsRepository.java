package oauth2.example.authorization.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oauth2.example.authorization.entity.OAuth2ClientDetails;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * <p>创建时间: 2021/1/30 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Repository
public interface OAuth2ClientDetailsRepository extends BaseMapper<OAuth2ClientDetails> {

}
