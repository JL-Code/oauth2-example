package org.example.oauth2.security.authentication;

import org.example.oauth2.security.OAuth2AdditionalAuthentication;
import org.example.oauth2.service.UaaUserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.Authentication;
import org.springframework.util.Assert;

public abstract class AbstractAdditionalAuthenticator implements Authenticator, InitializingBean {

    private UaaUserService uaaUserService;

    protected AbstractAdditionalAuthenticator(UaaUserService uaaUserService) {
        this.uaaUserService = uaaUserService;
    }

    public abstract void prepareAuthenticate(Authentication authentication);

    public abstract Authentication authenticate(Authentication authentication);

    public abstract boolean supports(OAuth2AdditionalAuthentication authentication);

    public UaaUserService getUaaUserService() {
        return uaaUserService;
    }

    public void setUaaUserService(UaaUserService uaaUserService) {
        this.uaaUserService = uaaUserService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.state(uaaUserService==null, "必须提供 uaaUserService");
    }
}
