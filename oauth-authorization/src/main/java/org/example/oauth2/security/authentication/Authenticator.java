package org.example.oauth2.security.authentication;

import org.example.oauth2.security.OAuth2AdditionalAuthentication;
import org.springframework.security.core.Authentication;

/**
 * 认证器接口
 * 提供了认证方法接口
 */
public interface Authenticator {

    /**
     * 为认证筹备, 进行各项准备工作
     *
     * @param authentication
     */
    void prepareAuthenticate(Authentication authentication);

    Authentication authenticate(Authentication authentication);

    boolean supports(OAuth2AdditionalAuthentication authentication);
}
