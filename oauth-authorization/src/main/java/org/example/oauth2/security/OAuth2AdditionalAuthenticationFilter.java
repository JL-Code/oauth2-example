package org.example.oauth2.security;

import org.example.oauth2.security.authentication.Authenticator;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class OAuth2AdditionalAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {

    public static final String ADDITIONAL_AUTH_URL = "/oauth/token";
    public static final String AUTH_SCHEME_PARAM_NAME = "auth_scheme";

    private ApplicationContext applicationContext;
    private Collection<Authenticator> authenticators;
    private RequestMatcher requiresAuthenticationRequestMatcher;

    public OAuth2AdditionalAuthenticationFilter() {
        requiresAuthenticationRequestMatcher = new OrRequestMatcher(
                new AntPathRequestMatcher(ADDITIONAL_AUTH_URL, "GET"),
                new AntPathRequestMatcher(ADDITIONAL_AUTH_URL, "POST"));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (requiresAuthentication(req, res)) {
            // 构建 oauth2 额外认证主体
            OAuth2AdditionalAuthentication authToken = new OAuth2AdditionalAuthentication();
            authToken.setParameterMap(req.getParameterMap());
            authToken.setAuthScheme(req.getParameter(AUTH_SCHEME_PARAM_NAME));

            this.prepareAuthenticate(authToken);
        }
        chain.doFilter(req, res);
    }

    private void prepareAuthenticate(OAuth2AdditionalAuthentication authentication) {
        for (Authenticator authenticator : this.getDefaultAuthenticators()) {
            if (authenticator.supports(authentication)) {
                authenticator.prepareAuthenticate(authentication);
            }
        }
    }

    /**
     * 判断当前请求是否需要认证.
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return 认证标志： true 当前请求需要认证、false 当前请求不需要认证
     */
    protected boolean requiresAuthentication(final HttpServletRequest request,
                                             final HttpServletResponse response) {
        return requiresAuthenticationRequestMatcher.matches(request);
    }

    /**
     * 设置 ApplicationContext，该方法用于接收 applicationContext 实例.
     *
     * @param context 应用上下文
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.applicationContext = context;
    }

    /**
     * 获取认证器集合.
     *
     * @return 返回认证器集合.
     */
    public Collection<Authenticator> getDefaultAuthenticators() {
        //延迟加载认证器
        if (this.authenticators == null) {
            synchronized (this) {
                Map<String, Authenticator> authenticatorMap = this.applicationContext.getBeansOfType(Authenticator.class);
                if (authenticatorMap != null) {
                    this.authenticators = authenticatorMap.values();
                }
            }
        }

        if (this.authenticators == null) {
            this.authenticators = new ArrayList<>();
        }

        return authenticators;
    }
}
