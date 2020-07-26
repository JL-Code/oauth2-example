package org.example.oauth2.security.wechat.corp;

import org.example.oauth2.security.OAuth2AdditionalAuthentication;
import org.example.oauth2.security.authentication.AuthorizationCodeAuthenticator;
import org.springframework.stereotype.Component;

@Component
public class CorpWeChatScanCodeAuthenticator extends AuthorizationCodeAuthenticator {

    private final static String AUTH_TYPE = "CORP_WECHAT_SCAN_CODE";

    @Override
    public boolean supports(OAuth2AdditionalAuthentication authentication) {
        return AUTH_TYPE.equalsIgnoreCase(authentication.getAuthType());
    }
}
