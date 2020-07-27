package org.example.oauth2.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
public class OAuth2AdditionalConfiguration extends AuthorizationServerConfigurerAdapter {

    private final RedisConnectionFactory redisConnectionFactory;

    public OAuth2AdditionalConfiguration(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public OAuth2AdditionalAuthenticationFilter oAuth2AdditionalAuthenticationFilter() {
        return new OAuth2AdditionalAuthenticationFilter();
    }

    @Bean
    public FilterRegistrationBean registration(OAuth2AdditionalAuthenticationFilter oAuth2AdditionalAuthenticationFilter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(oAuth2AdditionalAuthenticationFilter);
        registration.setEnabled(false);
        return registration;
    }

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
