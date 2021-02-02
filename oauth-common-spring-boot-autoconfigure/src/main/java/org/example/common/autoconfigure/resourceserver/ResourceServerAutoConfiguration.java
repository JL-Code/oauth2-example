package org.example.common.autoconfigure.resourceserver;

import lombok.extern.slf4j.Slf4j;
import org.example.common.autoconfigure.endpoint.OAuth2ResourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/22 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
@EnableConfigurationProperties(OAuth2ResourceProperties.class)
@ConditionalOnProperty(prefix = "oauth2-resource", name = "enabled", havingValue = "true", matchIfMissing = false)
@ConditionalOnWebApplication
@EnableResourceServer
@Slf4j
public class ResourceServerAutoConfiguration extends ResourceServerConfigurerAdapter {

    private RemoteTokenServices remoteTokenServices;

    public ResourceServerAutoConfiguration(OAuth2ResourceProperties properties) {
        remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(properties.getCheckTokenEndpoint());
        remoteTokenServices.setClientId(properties.getClientId());
        remoteTokenServices.setClientSecret(properties.getClientSecret());
        log.info("资源服务器配置初始中: checkToken:{} clientId:{} clientSecret:{}",
                properties.getCheckTokenEndpoint(), properties.getClientId(), properties.getClientSecret());
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(remoteTokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
