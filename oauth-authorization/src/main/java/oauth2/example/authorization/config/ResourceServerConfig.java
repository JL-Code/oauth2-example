//package oauth2.example.authorization.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
//
///**
// * <p>创建时间: 2021/2/1 </p>
// *
// * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
// * @version v1.0
// */
//@Configuration
//@EnableResourceServer
//public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder passwordEncoder =
//                PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return passwordEncoder;
//    }
//
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        // super.configure(http);
//        http
//                .formLogin()
//                // 登录静态页面地址
//                .loginPage("/login.html")
//                // 登录请求处理地址
//                .loginProcessingUrl("/login")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login.html")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                // 禁用 session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                // 禁用  httpBasic Filter 和  csrf Filter
//                .httpBasic().disable()
//                .csrf().disable();
//    }
//
//}
