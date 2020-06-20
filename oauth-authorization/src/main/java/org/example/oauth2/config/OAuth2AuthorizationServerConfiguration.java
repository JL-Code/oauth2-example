package org.example.oauth2.config;

import org.example.oauth2.userdetails.PlatformUserDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/18 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private RedisConnectionFactory redisConnectionFactory;
    private PlatformUserDetailsService platformUserDetailsService;
    private AuthenticationManager authenticationManager;

    public OAuth2AuthorizationServerConfiguration(RedisConnectionFactory redisConnectionFactory,
                                                  PlatformUserDetailsService platformUserDetailsService,
                                                  AuthenticationManager authenticationManager) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.platformUserDetailsService = platformUserDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /**
         * 如果需要自定义客户端信息服务，实现 {@link ClientDetailsService} 即可。
         **/
        // 配置客户端信息
        clients.inMemory()
                .withClient("cdb")
                .secret("cdb")
                .authorizedGrantTypes("password", "client_credentials","refresh_token", "authorization_code");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory)) // 设置 Redis 令牌存储服务
                .userDetailsService(platformUserDetailsService) // 用户服务
                .authenticationManager(authenticationManager) // 手动注入 authenticationManager 用于开启密码授权
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }
}
