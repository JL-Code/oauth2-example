package org.example.oauth2.security;

import org.springframework.security.core.Authentication;

public abstract class AbstractAdditionalAuthenticator implements Authenticator {

    public abstract Authentication authenticate(Authentication authentication);

    public abstract boolean supports(OAuth2AdditionalAuthentication authentication);
}
