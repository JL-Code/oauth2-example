package org.example.oauth2.provider.authenticator;

import org.example.oauth2.entity.User;
import org.example.oauth2.provider.IntegrationAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    // 用户 Provider

    @Override
    public User authenticate(IntegrationAuthentication integrationAuthentication) {
//        User user = sysUserClient.findUserByUsername(integrationAuthentication.getUsername());
//        return sysUserAuthentication;
        return null;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
    }
}
