package org.example.oauth2.security.wechat.corp;

import org.example.oauth2.security.AbstractAdditionalAuthenticator;
import org.example.oauth2.security.OAuth2AdditionalAuthentication;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class CorpScanCodeAuthenticator extends AbstractAdditionalAuthenticator {

    private final static String AUTH_TYPE = "CORP_WECHAT_SCAN_CODE";

    @Override
    public Authentication authenticate(Authentication authentication) {
        return null;
    }

    @Override
    public boolean supports(OAuth2AdditionalAuthentication authentication) {
        return AUTH_TYPE.equalsIgnoreCase(authentication.getAuthType());
    }
}
