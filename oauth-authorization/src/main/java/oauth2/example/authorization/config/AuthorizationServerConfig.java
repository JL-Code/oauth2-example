package oauth2.example.authorization.config;

import oauth2.example.authorization.security.CustomizedRedirectResolver;
import oauth2.example.authorization.security.model.UserIdentity;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

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
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    // 配置客户端信息

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("clientId")
                .secret("{noop}clientSecret")
                .authorizedGrantTypes("authorization_code")
                .scopes("snsapi_base", "snsapi_userinfo")
                .autoApprove("snsapi_base", "snsapi_userinfo")
//                .redirectUris("http://localhost:8081/oauth2/transfer-page");
                /**
                 * 默认的重定向为全匹配 scheme、userInfo、host、port、queryParam
                 * protected boolean redirectMatches(String requestedRedirect, String redirectUri) {
                 * 		UriComponents requestedRedirectUri = UriComponentsBuilder.fromUriString(requestedRedirect).build();
                 * 		UriComponents registeredRedirectUri = UriComponentsBuilder.fromUriString(redirectUri).build();
                 *
                 * 		boolean schemeMatch = isEqual(registeredRedirectUri.getScheme(), requestedRedirectUri.getScheme());
                 * 		boolean userInfoMatch = isEqual(registeredRedirectUri.getUserInfo(), requestedRedirectUri.getUserInfo());
                 * 		boolean hostMatch = hostMatches(registeredRedirectUri.getHost(), requestedRedirectUri.getHost());
                 * 		boolean portMatch = matchPorts ? registeredRedirectUri.getPort() == requestedRedirectUri.getPort() : true;
                 * 		boolean pathMatch = isEqual(registeredRedirectUri.getPath(),
                 * 				StringUtils.cleanPath(requestedRedirectUri.getPath()));
                 * 		boolean queryParamMatch = matchQueryParams(registeredRedirectUri.getQueryParams(),
                 * 				requestedRedirectUri.getQueryParams());
                 *
                 * 		return schemeMatch && userInfoMatch && hostMatch && portMatch && pathMatch && queryParamMatch;
                 * }
                 */
                // 重定向地址需要带上 scheme
                .redirectUris("http://localhost:8081", "https://localhost:8081");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.redirectResolver(new CustomizedRedirectResolver());
        endpoints.tokenEnhancer(new TokenEnhancer() {
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
                UserIdentity identity = (UserIdentity) authentication.getPrincipal();

                // 术语解释： Principal 主体  Identity 身份 Role 角色。
                // Principal = Identity + Role
                // https://stackoverflow.com/questions/28436332/what-is-really-a-principal-in-net
                // https://docs.microsoft.com/en-us/dotnet/standard/security/principal-and-identity-objects

                Map<String, Object> hash = new LinkedHashMap();
                hash.put("userid", identity.getId());

                token.setAdditionalInformation(hash);

                return token;
            }
        });
    }
}
