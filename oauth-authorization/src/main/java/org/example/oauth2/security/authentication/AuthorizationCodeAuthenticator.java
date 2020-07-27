package org.example.oauth2.security.authentication;

import lombok.var;
import org.example.oauth2.security.AdditionalAuthorizationCodeServices;
import org.example.oauth2.security.OAuth2AdditionalAuthentication;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;

/**
 * 授权码认证器.
 */
public abstract class AuthorizationCodeAuthenticator implements Authenticator, InitializingBean {

    public static final String AUTH_CODE_PARAM_NAME = "code";
    public static final String CLIENT_ID_PARAM_NAME = "client_id";

    private OAuth2RequestFactory oAuth2RequestFactory;
    private OAuth2RequestFactory defaultOAuth2RequestFactory;
    @Autowired
    private AdditionalAuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Override
    public void prepareAuthenticate(OAuth2AdditionalAuthentication authentication) throws AuthenticationException {

        //获取用户信息： 1. 通过授权码换取企业微信用户信息 2.通过微信用户信息获取自有系统用户信息
        var code = authentication.getParameter(AUTH_CODE_PARAM_NAME);
        var clientId = authentication.getParameter(CLIENT_ID_PARAM_NAME);
        authentication.setPrincipal(code);

        var clientDetails = this.getClientDetailsService().loadClientByClientId(clientId);

        var authorizationRequest =
                getOAuth2RequestFactory().createAuthorizationRequest(authentication.getParameters());
        var storedOAuth2Request = getOAuth2RequestFactory().createOAuth2Request(authorizationRequest);

        // 子类重写
        var userAuth = consumerAuthorizationCode(authentication, clientDetails);

        var oauth2Auth = new OAuth2Authentication(storedOAuth2Request, userAuth);

        // 创建授权码，并以授权码为 key 认证主体为 value 存储。
        authorizationCodeServices.storeAuthentication(code, oauth2Auth);
    }

    /**
     * 消费 OAuth2Server 提供的授权码以换取用户标识,由子类重写.
     *
     * @param authentication
     * @param clientDetails  当前客户端信息
     * @return
     */
    protected abstract OAuth2AdditionalAuthentication consumerAuthorizationCode(OAuth2AdditionalAuthentication authentication,
                                                                                ClientDetails clientDetails) throws AuthenticationException;

    @Override
    public Authentication authenticate(OAuth2AdditionalAuthentication authentication) {
        return null;
    }

    public OAuth2RequestFactory getOAuth2RequestFactory() {
        return oAuth2RequestFactory;
    }

    public ClientDetailsService getClientDetailsService() {
        return clientDetailsService;
    }

    /**
     * 可由子类设置客户端详情服务.
     *
     * @param clientDetailsService
     */
    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        if (clientDetailsService != null) {
            this.clientDetailsService = clientDetailsService;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(getClientDetailsService());
        if (oAuth2RequestFactory == null) {
            oAuth2RequestFactory = defaultOAuth2RequestFactory;
        }
    }
}
