//package org.example.oauth2.security.wechat.corp;
//
//import org.example.oauth2.entity.UaaUser;
//import org.example.oauth2.security.OAuth2AdditionalAuthentication;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.core.Authentication;
//
//class CorpWeChatScanCodeAuthenticatorTest {
//
//    @Test
//    public void testPassByReference() {
//
//        CorpWeChatScanCodeAuthenticator authenticator = new CorpWeChatScanCodeAuthenticator() {
//            @Override
//            public void prepareAuthenticate(Authentication authentication) {
//                authentication.setAuthenticated(true);
//                UaaUser uaaUser = new UaaUser();
//                uaaUser.setName("测试");
//                uaaUser.setUserCode("jiangy");
//                if (authentication instanceof OAuth2AdditionalAuthentication) {
//                    OAuth2AdditionalAuthentication oAuth2AdditionalAuthentication =
//                            (OAuth2AdditionalAuthentication) authentication;
//                    oAuth2AdditionalAuthentication.setPrincipal(uaaUser);
//                }
//            }
//        };
//
//        OAuth2AdditionalAuthentication authentication = new OAuth2AdditionalAuthentication();
//
//        authenticator.prepareAuthenticate(authentication);
//
//        Assertions.assertEquals(true, authentication.isAuthenticated());
//        Assertions.assertNotNull(authentication.getPrincipal());
//    }
//}