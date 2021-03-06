package org.example.oauth2.security;

import org.springframework.security.oauth2.provider.OAuth2Authentication;

public interface AdditionalAuthorizationCodeServices {

    /**
     * 以授权码为 key，认证主体为 value 存储到 Redis 中，为在客户端在消费 code 时提供认证主体信息。
     * @param authorizationCode 指定的授权码
     * @param authentication 指定的认证主体
     */
    void storeAuthentication(String authorizationCode, OAuth2Authentication authentication);
}
