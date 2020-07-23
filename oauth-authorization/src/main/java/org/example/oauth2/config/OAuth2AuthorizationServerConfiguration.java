package org.example.oauth2.config;

import org.example.oauth2.provider.IntegrationAuthenticationFilter;
import org.example.oauth2.provider.IntegrationUserDetailsService;
import org.example.oauth2.userdetails.PlatformUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.ArrayList;

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
    private IntegrationUserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    @Autowired
    private IntegrationAuthenticationFilter integrationAuthenticationFilter;


    public OAuth2AuthorizationServerConfiguration(RedisConnectionFactory redisConnectionFactory,
                                                  IntegrationUserDetailsService userDetailsService,
                                                  AuthenticationManager authenticationManager) {
        this.redisConnectionFactory = redisConnectionFactory;
        this.userDetailsService = userDetailsService;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        /**
         * 如果需要自定义客户端信息服务，实现 {@link ClientDetailsService} 即可。
         **/
        // 配置客户端信息
        ArrayList<String> clientCodes = new ArrayList<>();
        clientCodes.add("org");
        clientCodes.add("cdb");
        clientCodes.add("gateway");
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        clientCodes.stream().forEach(code -> {
            try {
                builder.withClient(code)
                        .secret(passwordEncoder.encode(code))
                        .redirectUris("http://www.baidu.com")
                        .authorizedGrantTypes("password", "client_credentials", "refresh_token", "authorization_code");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(new RedisTokenStore(redisConnectionFactory)) // 设置 Redis 令牌存储服务
                .userDetailsService(userDetailsService) // 用户服务
                .authenticationManager(authenticationManager) // 手动注入 authenticationManager 用于开启密码授权
                .allowedTokenEndpointRequestMethods(HttpMethod.POST);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("permitAll()")
                .tokenKeyAccess("permitAll()")
                .allowFormAuthenticationForClients()
                .addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);
    }
}
