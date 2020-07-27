package org.example.oauth2.security;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>创建时间: 2020/7/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class OAuth2AdditionalException extends AuthenticationException {
    /**
     * Constructs an {@code AuthenticationException} with the specified message and root
     * cause.
     *
     * @param msg the detail message
     * @param t   the root cause
     */
    public OAuth2AdditionalException(String msg, Throwable t) {
        super(msg, t);
    }

    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public OAuth2AdditionalException(String msg) {
        super(msg);
    }
}
