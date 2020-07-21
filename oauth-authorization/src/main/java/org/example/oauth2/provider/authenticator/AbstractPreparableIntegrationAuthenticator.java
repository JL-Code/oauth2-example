package org.example.oauth2.provider.authenticator;

import org.example.oauth2.entity.User;
import org.example.oauth2.provider.IntegrationAuthentication;

/**
 * @author LIQIU
 * @date 2018-4-4
 **/
public abstract class AbstractPreparableIntegrationAuthenticator implements IntegrationAuthenticator {

    @Override
    public abstract User authenticate(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract void prepare(IntegrationAuthentication integrationAuthentication);

    @Override
    public abstract boolean support(IntegrationAuthentication integrationAuthentication);

    @Override
    public void complete(IntegrationAuthentication integrationAuthentication) {

    }
}
