package org.example.oauth2.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class OAuth2AdditionalAuthentication extends AbstractAuthenticationToken {
    private String authScheme;
    private Map<String, String[]> parameterMap;
    private Object principal;
    private Object credentials;
    private ClientDetails clientDetails;

    public OAuth2AdditionalAuthentication() {
        super(null);
        this.setAuthenticated(false);
    }

    public OAuth2AdditionalAuthentication(Object principal, Object credentials,
                                          Collection<? extends GrantedAuthority> authorities) {
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
        String[] values = this.parameterMap.get(parameter);
        if (values != null && values.length > 0) {
            return values[0];
        }
        return null;
    }

    public Map<String, String> getParameters() {
        return extractRequestParameters();
    }

    private Map<String, String> extractRequestParameters() {
        Map<String, String> parameters = new HashMap<>();
        this.parameterMap.forEach((k, values) -> {
            String value = (values != null && values.length > 0) ? values[0] : null;
            parameters.put(k, value);
        });
        return parameters;
    }
}
