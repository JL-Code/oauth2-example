package org.example.resource.endpoint;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
@RequestMapping("/api/user")
public class UserInfoEndpoint {

    private static final String AUTH = "auth:";
    private final String prefix = "";

    private final RedisConnectionFactory redisConnectionFactory;
    private RedisTokenStoreSerializationStrategy serializationStrategy = new JdkSerializationStrategy();

    public UserInfoEndpoint(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    private final RedisConnection getConnection() {
        return redisConnectionFactory.getConnection();
    }

    private final byte[] serializeKey(String object) {
        return serialize(prefix + object);
    }

    private byte[] serialize(String string) {
        return serializationStrategy.serialize(string);
    }

    @GetMapping("/userinfo")
    public Object getUserInfo(Authentication authentication) {
        String token = authentication.getPrincipal().toString();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();

        return details;
    }
}
