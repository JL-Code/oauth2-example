package org.example.oauth2.security;

import org.springframework.security.core.Authentication;

/**
 * 认证器接口
 * 提供了认证方法接口
 */
public interface Authenticator {

    Authentication authenticate(Authentication authentication);

    boolean supports(OAuth2AdditionalAuthentication authentication);
}
