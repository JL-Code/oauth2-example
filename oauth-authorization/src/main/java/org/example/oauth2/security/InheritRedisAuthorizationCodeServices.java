package org.example.oauth2.security;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.RedisAuthorizationCodeServices;

public class InheritRedisAuthorizationCodeServices extends RedisAuthorizationCodeServices implements AdditionalAuthorizationCodeServices {
    /**
     * Default constructor.
     *
     * @param connectionFactory the connection factory which should be used to obtain a connection to Redis
     */
    public InheritRedisAuthorizationCodeServices(RedisConnectionFactory connectionFactory) {
        super(connectionFactory);
    }

    @Override
    public void storeAuthentication(String authorizationCode, OAuth2Authentication authentication) {
        String code = authorizationCode;
        store(code, authentication);
    }
}
