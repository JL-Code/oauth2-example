package org.example.oauth2.security;

import com.google.common.collect.Maps;
import me.chanjar.weixin.cp.api.WxCpService;
import org.example.oauth2.security.wechat.corp.CorpWeChatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.RedisAuthorizationCodeServices;

import java.util.Map;

@Configuration
public class OAuth2AdditionalConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public OAuth2AdditionalAuthenticationFilter oAuth2AdditionalAuthenticationFilter() {
        return new OAuth2AdditionalAuthenticationFilter();
    }

    @Bean
    public FilterRegistrationBean registration(OAuth2AdditionalAuthenticationFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }

//    @Bean
//    public RedisAuthorizationCodeServices redisAuthorizationCodeServices() {
//        return new RedisAuthorizationCodeServices(redisConnectionFactory);
//    }

    @Bean
    public InheritRedisAuthorizationCodeServices inheritRedisAuthorizationCodeServices() {
        return new InheritRedisAuthorizationCodeServices(redisConnectionFactory);
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authorizationCodeServices(inheritRedisAuthorizationCodeServices());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.addTokenEndpointAuthenticationFilter(oAuth2AdditionalAuthenticationFilter());
    }
}
