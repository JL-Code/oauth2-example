package oauth2.example.authorization.config;

import oauth2.example.authorization.security.CustomizedRedirectResolver;
import oauth2.example.authorization.security.CustomizedTokenEnhancer;
import oauth2.example.authorization.security.service.CustomizedClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

/**
 * <p>创建时间: 2021/1/27 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
@EnableAuthorizationServer
@AutoConfigureAfter(AuthorizationServerEndpointsConfigurer.class)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private CustomizedClientDetailsService clientDetailsService;
    private CustomizedTokenEnhancer customizedTokenEnhancer;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    @Autowired
    private RandomValueAuthorizationCodeServices authorizationCodeServices;

    public AuthorizationServerConfig(CustomizedClientDetailsService clientDetailsService, CustomizedTokenEnhancer customizedTokenEnhancer, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.clientDetailsService = clientDetailsService;
        this.customizedTokenEnhancer = customizedTokenEnhancer;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * 默认的重定向为全匹配 scheme、userInfo、host、port、queryParam
     * protected boolean redirectMatches(String requestedRedirect, String redirectUri) {
     * UriComponents requestedRedirectUri = UriComponentsBuilder.fromUriString(requestedRedirect).build();
     * UriComponents registeredRedirectUri = UriComponentsBuilder.fromUriString(redirectUri).build();
     * <p>
     * boolean schemeMatch = isEqual(registeredRedirectUri.getScheme(), requestedRedirectUri.getScheme());
     * boolean userInfoMatch = isEqual(registeredRedirectUri.getUserInfo(), requestedRedirectUri.getUserInfo());
     * boolean hostMatch = hostMatches(registeredRedirectUri.getHost(), requestedRedirectUri.getHost());
     * boolean portMatch = matchPorts ? registeredRedirectUri.getPort() == requestedRedirectUri.getPort() : true;
     * boolean pathMatch = isEqual(registeredRedirectUri.getPath(),
     * StringUtils.cleanPath(requestedRedirectUri.getPath()));
     * boolean queryParamMatch = matchQueryParams(registeredRedirectUri.getQueryParams(),
     * requestedRedirectUri.getQueryParams());
     * <p>
     * return schemeMatch && userInfoMatch && hostMatch && portMatch && pathMatch && queryParamMatch;
     * }
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("isAuthenticated()")
                .checkTokenAccess("permitAll()")
                // 让 /oauth/token 支持 client_id + client_secret 作登录认证
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .redirectResolver(new CustomizedRedirectResolver())
//                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenEnhancer(customizedTokenEnhancer);
    }
}
