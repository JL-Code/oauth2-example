package oauth2.example.authorization.security;

import oauth2.example.authorization.security.model.UserIdentity;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>创建时间: 2021/2/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Component
public class CustomizedTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken token = (DefaultOAuth2AccessToken) accessToken;
        UserIdentity identity = (UserIdentity) authentication.getPrincipal();

        // 术语解释： Principal 主体  Identity 身份 Role 角色。
        // Principal = Identity + Role
        // https://stackoverflow.com/questions/28436332/what-is-really-a-principal-in-net
        // https://docs.microsoft.com/en-us/dotnet/standard/security/principal-and-identity-objects

        Map<String, Object> hash = new LinkedHashMap();
        hash.put("openid", identity.getId());
        hash.put("userid", identity.getId());

        token.setAdditionalInformation(hash);

        return token;
    }
}
