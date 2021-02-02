package oauth2.example.authorization.config;

import oauth2.example.authorization.security.CustomizedRedirectResolver;
import oauth2.example.authorization.security.model.UserIdentity;
import oauth2.example.authorization.security.service.CustomizedClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

import java.util.LinkedHashMap;
import java.util.Map;

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
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    @Autowired
    private RandomValueAuthorizationCodeServices authorizationCodeServices;

    public AuthorizationServerConfig(CustomizedClientDetailsService clientDetailsService, UserDetailsService userDetailsService, AuthenticationManager authenticationManager) {
        this.clientDetailsService = clientDetailsService;
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
                //让 /oauth/token 支持 client_id 以及 client_secret 作登录认证
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .redirectResolver(new CustomizedRedirectResolver())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .authorizationCodeServices(authorizationCodeServices)
                .tokenEnhancer((accessToken, authentication) -> {
                    DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                    UserIdentity identity = (UserIdentity) authentication.getPrincipal();

                    // 术语解释： Principal 主体  Identity 身份 Role 角色。
                    // Principal = Identity + Role
                    // https://stackoverflow.com/questions/28436332/what-is-really-a-principal-in-net
                    // https://docs.microsoft.com/en-us/dotnet/standard/security/principal-and-identity-objects

                    Map<String, Object> hash = new LinkedHashMap();
                    hash.put("openid", identity.getId());
                    hash.put("userid", identity.getId());

                    token.setAdditionalInformation(hash);

                    return token;
                });
    }
}
