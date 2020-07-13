package org.example.oauth.gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.support.DefaultServerCodecConfigurer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.introspection.NimbusReactiveOpaqueTokenIntrospector;
import org.springframework.security.oauth2.server.resource.introspection.ReactiveOpaqueTokenIntrospector;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Slf4j
@EnableWebFluxSecurity
public class ResourceServerConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers("/actuator/**").permitAll()
                .anyExchange().authenticated();

        http.oauth2ResourceServer().opaqueToken();

        return http.build();
    }

    @Bean
    public ReactiveOpaqueTokenIntrospector reactiveOpaqueTokenIntrospector() {
        return new NimbusReactiveOpaqueTokenIntrospector("http://localhost:8080/oauth/check_token", "gateway",
                "gateway");
    }

//    @Bean
//    public ServerCodecConfigurer serverCodecConfigurer() {
//        return new DefaultServerCodecConfigurer();
//    }
}
