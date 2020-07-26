package org.example.oauth2.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;

@Setter
@Getter
public class OAuth2AdditionalAuthentication extends AbstractAuthenticationToken {
    private String authType;
    private Map<String, String[]> parameters;
    private Object principal;
    private Object credentials;

    public OAuth2AdditionalAuthentication() {
        this(null);
    }

    public OAuth2AdditionalAuthentication(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }

    public String getParameter(String parameter) {
        String[] values = this.parameters.get(parameter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }
}
