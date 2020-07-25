package org.example.oauth2.provider.authenticator.sms;

import org.example.oauth2.dao.UserDao;
import org.example.oauth2.entity.User;
import org.example.oauth2.provider.IntegrationAuthentication;
import org.example.oauth2.provider.authenticator.AbstractPreparableIntegrationAuthenticator;
import org.example.oauth2.provider.authenticator.sms.event.SmsAuthenticateBeforeEvent;
import org.example.oauth2.provider.authenticator.sms.event.SmsAuthenticateSuccessEvent;
import org.example.oauth2.provider.service.CaptchaService;
import org.example.oauth2.provider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.stereotype.Component;

/**
 * 短信验证码集成认证
 *
 * @author LIQIU
 * @date 2018-3-31
 **/
@Component
public class SmsIntegrationAuthenticator extends AbstractPreparableIntegrationAuthenticator implements ApplicationEventPublisherAware {

    // TODO: 用户 Provider 、验证码 Provider
    @Autowired
    private UserService userService;
    @Autowired
    private CaptchaService captchaService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private ApplicationEventPublisher applicationEventPublisher;

    private final static String SMS_AUTH_TYPE = "sms";

    @Override
    public User authenticate(IntegrationAuthentication integrationAuthentication) {

        //获取密码，实际值是验证码
        String password = integrationAuthentication.getAuthParameter("password");
        //获取用户名，实际值是手机号
        String username = integrationAuthentication.getUsername();
        //发布事件，可以监听事件进行自动注册用户
        this.applicationEventPublisher.publishEvent(new SmsAuthenticateBeforeEvent(integrationAuthentication));
        //通过手机号码查询用户
        User User = this.userService.findUserByPhoneNumber(username);
        if (User != null) {
            //将密码设置为验证码
            User.setPassword(passwordEncoder.encode(password));
            //发布事件，可以监听事件进行消息通知
            this.applicationEventPublisher.publishEvent(new SmsAuthenticateSuccessEvent(integrationAuthentication));
        }
        return User;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {
        // 这里是短信验证码 Token
        String smsToken = integrationAuthentication.getAuthParameter("sms_token");
        // 这里是短信验证码
        String smsCode = integrationAuthentication.getAuthParameter("password");
        // 这里是手机号码
        String username = integrationAuthentication.getAuthParameter("username");

        // TODO: 验证码校验
        boolean result = this.captchaService.validate(smsToken, smsCode, username);
        if (!result) {
            throw new OAuth2Exception("验证码错误或已过期");
        }
    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return SMS_AUTH_TYPE.equals(integrationAuthentication.getAuthType());
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
