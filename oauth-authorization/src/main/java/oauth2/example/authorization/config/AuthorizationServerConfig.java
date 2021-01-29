package oauth2.example.authorization.config;

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
                .scopes("api_userinfo")
                .redirectUris("http://localhost:8081/oauth2/transfer-page");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
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
