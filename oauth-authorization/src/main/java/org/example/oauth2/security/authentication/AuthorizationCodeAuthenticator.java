package org.example.oauth2.security.authentication;

import org.springframework.security.core.Authentication;

/**
 * 授权码认证器
 */
public abstract class AuthorizationCodeAuthenticator implements Authenticator {

    @Override
    public void prepareAuthenticate(Authentication authentication) {

    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        return null;
    }
}
