package org.example.oauth2.security;

import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import org.example.oauth2.entity.UaaUser;
import org.example.oauth2.security.authentication.Authenticator;
import org.example.oauth2.service.UaaUserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.Assert;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class OAuth2AdditionalAuthenticationFilter extends GenericFilterBean implements ApplicationContextAware {

    public static final String OAUTH2_ADDITIONAL_AUTHENTICATION_URL = "/oauth/token";

    private ApplicationContext applicationContext;
    @Autowired
    private AdditionalAuthorizationCodeServices authorizationCodeServices;
    @Autowired
    private UaaUserService uaaUserService;
    private Collection<Authenticator> authenticators = Collections.emptyList();
    private RequestMatcher requiresAuthenticationRequestMatcher;
    @Autowired
    private ClientDetailsService clientDetailsService;

    public OAuth2RequestFactory getOAuth2RequestFactory() {
        return oAuth2RequestFactory;
    }

    private OAuth2RequestFactory oAuth2RequestFactory;
    private OAuth2RequestFactory defaultOAuth2RequestFactory;

    public OAuth2AdditionalAuthenticationFilter() {
        // 拦截 get、post 的 /oauth/token 请求
        requiresAuthenticationRequestMatcher =
                new OrRequestMatcher(new AntPathRequestMatcher(OAUTH2_ADDITIONAL_AUTHENTICATION_URL, "GET"),
                        new OrRequestMatcher(new AntPathRequestMatcher(OAUTH2_ADDITIONAL_AUTHENTICATION_URL,
                                "POST")));
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (requiresAuthentication(req, res)) {
            // 构建 oauth2 额外认证主体
            OAuth2AdditionalAuthentication authToken = new OAuth2AdditionalAuthentication();
            authToken.setAuthType(req.getParameter("auth_type"));
            authToken.setParameters(req.getParameterMap());

            Map<String, String> parameters = extractRequestParameters(req);
            // OAuth2Request storedRequest, Authentication userAuthentication
            // getOAuth2RequestFactory().createAuthorizationRequest(parameters);
            AuthorizationRequest authorizationRequest =
                    getOAuth2RequestFactory().createAuthorizationRequest(parameters);
            OAuth2Request storedOAuth2Request = getOAuth2RequestFactory().createOAuth2Request(authorizationRequest);
            //获取用户信息： 1. 通过授权码换取企业微信用户信息 2.通过微信用户信息获取自有系统用户信息
            String code = parameters.get("code");
            authToken.setPrincipal(code);
            // 自有应用于企业微信应用需要建立关联从而通过 client_id 获取 agentId
            String clientId = parameters.get("client_id");
            ClientDetails clientDetails = this.getClientDetailsService().loadClientByClientId(clientId);

            Authentication userAuth = getAuthentication(authToken, clientDetails);
            OAuth2Authentication oauth2Auth = new OAuth2Authentication(storedOAuth2Request, userAuth);
            // 创建授权码，并以授权码为 key 认证主体为 value 存储。
            authorizationCodeServices.storeAuthentication(code, oauth2Auth);
        }
        chain.doFilter(req, res);
    }

    /**
     * 获取认证主体信息
     * @param auth 从请求中提取信息创建的待认证主体
     * @param clientDetails 客户端详情
     * @return
     */
    private Authentication getAuthentication(OAuth2AdditionalAuthentication auth, ClientDetails clientDetails) {
        // ClientDetails 需要重写，重写后的类包含微信应用、钉钉应用配置等信息
//        String agentId = clientDetails.getAdditionalInformation().get("agentId").toString();
        // AccessToken 企业微信的 accessToken
        try {
//            WxCpService corpService = CorpWeChatConfiguration.getCorpService(Integer.valueOf(agentId));
//            WxCpOauth2UserInfo userInfo = corpService.getOauth2Service().getUserInfo(auth.getPrincipal().toString());
            // 模拟 WxCpOauth2UserInfo
            WxCpOauth2UserInfo userInfo = new WxCpOauth2UserInfo();
            userInfo.setUserId("JiangYong");
            /**
             * a) 当用户为企业成员时返回示例如下：
             *     {
             *        "errcode": 0,
             *        "errmsg": "ok",
             *        "UserId":"USERID"
             *     }
             */

            /**
             *  b) 非企业成员授权时返回示例如下：
             *     {
             *        "errcode": 0,
             *        "errmsg": "ok",
             *        "OpenId":"OPENID"
             *     }
             */
            if (StringUtils.isEmpty(userInfo.getUserId()) && StringUtils.isNotEmpty(userInfo.getOpenId())) {
                throw new OAuth2Exception("非企业成员授权");
            }

            UaaUser uaaUser = this.uaaUserService.findUserByCorpWeChatUserId(userInfo.getUserId());
            if (uaaUser == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
            auth.setPrincipal(uaaUser);
            auth.setAuthenticated(true);

        } catch (OAuth2Exception e) {
            // 包装异常向外抛出
            e.printStackTrace();
        }
        return auth;
    }


    private Map<String, String> extractRequestParameters(HttpServletRequest request) {
        Map<String, String> parameters = new HashMap<>();
        request.getParameterMap().forEach((k, values) -> {
            String value = (values != null && values.length > 0) ? values[0] : null;
            parameters.put(k, value);
        });
        return parameters;
    }

    protected boolean requiresAuthentication(HttpServletRequest request,
                                             HttpServletResponse response) {
        return requiresAuthenticationRequestMatcher.matches(request);
    }

    public ClientDetailsService getClientDetailsService() {
        return clientDetailsService;
    }

    public void setClientDetailsService(ClientDetailsService clientDetailsService) {
        this.clientDetailsService = clientDetailsService;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    protected void initFilterBean() throws ServletException {
        Assert.state(clientDetailsService != null, "ClientDetailsService must be provided");
        this.authenticators =
                this.applicationContext.getBeansOfType(Authenticator.class).values();
        defaultOAuth2RequestFactory = new DefaultOAuth2RequestFactory(getClientDetailsService());
        if (oAuth2RequestFactory == null) {
            oAuth2RequestFactory = defaultOAuth2RequestFactory;
        }
    }
}
