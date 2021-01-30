package oauth2.example.authorization.security.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

/**
 * <p>创建时间: 2021/1/30 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface CustomizedClientDetailsService extends ClientDetailsService {

    void setPasswordEncoder(PasswordEncoder passwordEncoder);

    boolean addClientDetails(ClientDetails clientDetails);

    boolean updateClientDetails(ClientDetails clientDetails);

    boolean deleteByClientId(String clientId);
}
