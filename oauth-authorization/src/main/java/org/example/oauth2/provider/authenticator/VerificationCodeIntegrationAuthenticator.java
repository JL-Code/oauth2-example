package org.example.oauth2.provider.authenticator;

import org.example.oauth2.provider.IntegrationAuthentication;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * 集成验证码认证
 * @author LIQIU
 * @date 2018-3-31
 **/
@Component
public class VerificationCodeIntegrationAuthenticator extends UsernamePasswordAuthenticator {

    private final static String VERIFICATION_CODE_AUTH_TYPE = "vc";
    // 验证码 Provider

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
        String vcToken = integrationAuthentication.getAuthParameter("vc_token");
        String vcCode = integrationAuthentication.getAuthParameter("vc_code");
        //验证验证码
//        Result<Boolean> result = verificationCodeClient.validate(vcToken, vcCode, null);
//        if (!result.getData()) {
//            throw new OAuth2Exception("验证码错误");
//        }
    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return VERIFICATION_CODE_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
    }
}
