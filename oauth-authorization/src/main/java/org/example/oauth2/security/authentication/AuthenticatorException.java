package org.example.oauth2.security.authentication;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

/**
 * 认证器异常
 */
public class AuthenticatorException extends OAuth2Exception {

    public AuthenticatorException(String msg) {
        super(msg);
    }

    public AuthenticatorException(String msg, Throwable t) {
        super(msg, t);
    }
}
