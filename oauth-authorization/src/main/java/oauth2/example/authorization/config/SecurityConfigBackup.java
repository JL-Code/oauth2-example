//package oauth2.example.authorization.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * <p>创建时间: 2021/1/27 </p>
// *
// * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
// * @version v1.0
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private UserDetailsService userDetailsService;
//
//    public SecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    /**
//     * 启用  Resource Owner Password Credentials Grant
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        PasswordEncoder passwordEncoder =
//                PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        return passwordEncoder;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
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
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService);
////        auth.inMemoryAuthentication()
////                .withUser("admin")
////                /**
////                 * 问题现象：There is no PasswordEncoder mapped for the id "null"
////                 * 问题原因：spring security 5.x 开始调整了密码的格式:"{id}encodedPassword"。 https://docs.spring.io/spring-security/site/docs/5.2.8.RELEASE/reference/htmlsingle/#pe-dpe-formatc/main/java/org/springframework/security/crypto/factory/PasswordEncoderFactories.java
////                 * 解决方法：https://blog.csdn.net/Hello_World_QWP/article/details/81811462
////                 * https://github.com/spring-projects/spring-security/blob/7ef3f619242816683a72b35a1f8b4fb4f32d5203/crypto/sr          */
////                .password("{noop}123")
////                .authorities("ROLE_ADMIN");
//    }
//}
