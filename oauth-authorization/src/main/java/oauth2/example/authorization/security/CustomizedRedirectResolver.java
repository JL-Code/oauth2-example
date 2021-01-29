package oauth2.example.authorization.security;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.endpoint.DefaultRedirectResolver;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * <p>创建时间: 2021/1/29 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public class CustomizedRedirectResolver extends DefaultRedirectResolver {
    /**
     * 重写父类的重定向地址匹配，仅匹配 host
     *
     * @param requestedRedirect
     * @param redirectUri
     * @return
     */
    @Override
    protected boolean redirectMatches(String requestedRedirect, String redirectUri) {
        UriComponents requestedRedirectUri = UriComponentsBuilder.fromUriString(requestedRedirect).build();
        UriComponents registeredRedirectUri = UriComponentsBuilder.fromUriString(redirectUri).build();

        boolean hostMatch = hostMatches(registeredRedirectUri.getHost(), requestedRedirectUri.getHost());

        return hostMatch;
    }

    @Override
    public String resolveRedirect(String requestedRedirect, ClientDetails client) throws OAuth2Exception {
        super.resolveRedirect(requestedRedirect, client);
        System.out.println(String.format("requestedRedirect: %s", requestedRedirect));
        return requestedRedirect;
    }

}
