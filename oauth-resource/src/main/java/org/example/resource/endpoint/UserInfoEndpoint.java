package org.example.resource.endpoint;

import org.example.common.model.PlatformUserDetails;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.store.redis.JdkSerializationStrategy;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
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

    @GetMapping("/user/info")
    public Object getUserInfo(Authentication authentication) {
        String token = authentication.getPrincipal().toString();
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        String tokenValue = details.getTokenValue();
        byte[] authKey = serializeKey(AUTH + tokenValue);
        byte[] bytes = getConnection().get(authKey);
        OAuth2Authentication auth2Authentication = serializationStrategy.deserialize(bytes,
                OAuth2Authentication.class);
        Authentication userAuthentication = auth2Authentication.getUserAuthentication();
        PlatformUserDetails userDetails = (PlatformUserDetails) userAuthentication.getPrincipal();
        userDetails.setPassword("");
        return userDetails;
    }
}
