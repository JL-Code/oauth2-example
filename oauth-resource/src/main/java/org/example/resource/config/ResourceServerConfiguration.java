package org.example.resource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * <p>描述: 资源服务器配置信息 </p>
 * <p>创建时间: 2020/6/19 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    // oauth/check_token 用于资源服务器像授权服务器请求检查令牌有效性
    private final String checkTokenEndpoint = "http://localhost:8080/oauth/check_token";
    private final String clientId = "cdb";
    private final String clientSecret = "cdb";
    private RemoteTokenServices remoteTokenServices;

    public ResourceServerConfiguration() {
        remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setCheckTokenEndpointUrl(checkTokenEndpoint);
        remoteTokenServices.setClientId(clientId);
        remoteTokenServices.setClientSecret(clientSecret);
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenServices(remoteTokenServices);
//                .accessDeniedHandler(new OAuth2AccessDeniedHandler()); // 接管错误处理器
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/oauth/**")
                .permitAll();
        super.configure(http);
    }
}
