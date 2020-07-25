package org.example.oauth2.provider;

import org.example.oauth2.provider.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaEndpoint {

    @Autowired
    private CaptchaService captchaService;

    /**
     * 获取手机验证码
     *
     * @param subject 手机号码
     * @return
     */
    @GetMapping("/oauth/captcha")
    public Object getCaptcha(String subject) {
        return captchaService.getToken(subject);
    }
}
