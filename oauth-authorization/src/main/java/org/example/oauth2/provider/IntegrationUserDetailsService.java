package org.example.oauth2.provider;

import org.example.common.model.PlatformUserDetails;
import org.example.oauth2.entity.User;
import org.example.oauth2.provider.authenticator.IntegrationAuthenticator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntegrationUserDetailsService implements UserDetailsService {


    private List<IntegrationAuthenticator> authenticators;

    @Autowired(required = false)
    public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
        this.authenticators = authenticators;
    }

    @Override
    public PlatformUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
        }
        integrationAuthentication.setUsername(username);
        User user = this.authenticate(integrationAuthentication);

        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        PlatformUserDetails platformUser = new PlatformUserDetails();

        BeanUtils.copyProperties(user, platformUser);

        return platformUser;

    }


    private User authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        return null;
    }
}