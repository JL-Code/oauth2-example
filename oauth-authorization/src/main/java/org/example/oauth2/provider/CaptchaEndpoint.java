package org.example.oauth2.provider;

import org.example.oauth2.provider.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CaptchaEndpoint {

    @Autowired
    private CaptchaService captchaService;

    @GetMapping("/oauth/captcha")
    public Object methodName(String phoneNumber) {
        return captchaService.getToken(phoneNumber);
    }
}
