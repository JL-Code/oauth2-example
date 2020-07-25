package org.example.oauth2.provider.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String getToken(String subject) {
        return this.getToken(null, null, null, subject);
    }

    @Override
    public String getToken(Integer size, Long expire, String type, String subject) {
        String code = "6666";
        String realToken = subject + "@" + code;
        stringRedisTemplate.opsForValue().set(realToken, code);
        return code;
    }

    @Override
    public void refresh(String token) {

    }

    @Override
    public void refresh(String token, long expire) {

    }

    @Override
    public void sendSms(String token, String phoneNumber) {

    }

    @Override
    public void sendSms(String token, String templateCode, String signName, String phoneNumber, String codeParamName, Map<String, Object> params) {

    }

    @Override
    public void renderImage(String token, OutputStream outputStream) throws IOException {

    }

    @Override
    public boolean validate(String token, String code, String phoneNumber) {
        return this.validate(token, code, phoneNumber, true);
    }

    @Override
    public boolean validate(String token, String code, String phoneNumber, boolean ignoreCase) {
        if (StringUtils.isEmpty(token)) {
            token = phoneNumber + "@" + code;
        }
        // TODO: 验证码校验逻辑
        String smsCode = stringRedisTemplate.opsForValue().get(token);
        if (smsCode == null) {
            smsCode = "";
            System.out.println("smsCode is null");
        }

        return ignoreCase ? smsCode.equalsIgnoreCase(code) : smsCode.equals(code);
    }
}
