//package oauth2.client.sample.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProvider;
//import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder;
//import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
//import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
//import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
//
///**
// * <p>创建时间: 2021/2/8 </p>
// *
// * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
// * @version v1.0
// */
//@EnableWebSecurity
//public class OAuth2ClientSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Bean
//    public OAuth2AuthorizedClientManager authorizedClientManager(
//            ClientRegistrationRepository clientRegistrationRepository,
//            OAuth2AuthorizedClientRepository authorizedClientRepository) {
//
//        OAuth2AuthorizedClientProvider authorizedClientProvider =
//                OAuth2AuthorizedClientProviderBuilder.builder()
//                        .authorizationCode()
//                        .refreshToken()
//                        .clientCredentials()
//                        .password()
//                        .build();
//
//        DefaultOAuth2AuthorizedClientManager authorizedClientManager =
//                new DefaultOAuth2AuthorizedClientManager(
//                        clientRegistrationRepository, authorizedClientRepository);
//        authorizedClientManager.setAuthorizedClientProvider(authorizedClientProvider);
//
//        return authorizedClientManager;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .oauth2Client(oauth2Client ->
//                        oauth2Client
//                                .clientRegistrationRepository(this.clientRegistrationRepository())
//                                .authorizedClientRepository(this.authorizedClientRepository())
//                                .authorizedClientService(this.authorizedClientService())
//                                .authorizationCodeGrant(authorizationCodeGrant ->
//                                        authorizationCodeGrant
//                                                .authorizationRequestRepository(this.authorizationRequestRepository())
//                                                .authorizationRequestResolver(this.authorizationRequestResolver())
//                                                .accessTokenResponseClient(this.accessTokenResponseClient())
//                                )
//                );
//    }
//}
