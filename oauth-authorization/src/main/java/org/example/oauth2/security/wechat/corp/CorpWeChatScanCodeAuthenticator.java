package org.example.oauth2.security.wechat.corp;

import lombok.var;
import me.chanjar.weixin.cp.bean.WxCpOauth2UserInfo;
import org.apache.commons.lang3.StringUtils;
import org.example.oauth2.security.OAuth2AdditionalAuthentication;
import org.example.oauth2.security.authentication.AuthorizationCodeAuthenticator;
import org.example.oauth2.service.UaaUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.stereotype.Component;

@Component
public class CorpWeChatScanCodeAuthenticator extends AuthorizationCodeAuthenticator {

    private static final String AUTH_SCHEME = "CORP_WECHAT_SCAN_CODE";

    private UaaUserService uaaUserService;

    public CorpWeChatScanCodeAuthenticator(UaaUserService uaaUserService) {
        this.uaaUserService = uaaUserService;
    }

    @Override
    public boolean supports(OAuth2AdditionalAuthentication authentication) {
        return AUTH_SCHEME.equalsIgnoreCase(authentication.getAuthScheme());
    }

    /**
     * a) 当用户为企业成员时返回示例如下：
     * {
     * "errcode": 0,
     * "errmsg": "ok",
     * "UserId":"USERID"
     * }
     * b) 非企业成员授权时返回示例如下：
     * {
     * "errcode": 0,
     * "errmsg": "ok",
     * "OpenId":"OPENID"
     * }
     */
    @Override
    protected OAuth2AdditionalAuthentication consumerAuthorizationCode(OAuth2AdditionalAuthentication authentication, ClientDetails clientDetails) {
        return mockConsumerAuthCode(authentication, clientDetails);
    }

    private OAuth2AdditionalAuthentication mockConsumerAuthCode(OAuth2AdditionalAuthentication authentication,
                                                                ClientDetails clientDetails) {

//        var agentId = clientDetails.getAdditionalInformation().get("agentId").toString();
        // AccessToken 企业微信的 accessToken
        try {
//            WxCpService corpService = CorpWeChatConfiguration.getCorpService(Integer.valueOf(agentId));
//            WxCpOauth2UserInfo userInfo = corpService.getOauth2Service().getUserInfo(auth.getPrincipal().toString());
            // 模拟 WxCpOauth2UserInfo
            var userInfo = new WxCpOauth2UserInfo();
            userInfo.setUserId("JiangYong");
            if (StringUtils.isEmpty(userInfo.getUserId()) && StringUtils.isNotEmpty(userInfo.getOpenId())) {
                throw new OAuth2Exception("非企业成员授权");
            }

            var uaaUser = this.uaaUserService.findUserByCorpWeChatUserId(userInfo.getUserId());
            if (uaaUser == null) {
                throw new UsernameNotFoundException("用户不存在");
            }
            authentication.setPrincipal(uaaUser);
            authentication.setAuthenticated(true);

        } catch (OAuth2Exception e) {
            // 包装异常向外抛出
            e.printStackTrace();
        }

        return authentication;
    }
}
