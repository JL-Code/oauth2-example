package org.example.resource.endpoint;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>创建时间: 2021/2/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
@RequestMapping("/oauth2/v1")
public class OAuth2UserInfoEndpoint {

    @GetMapping("userinfo")
    public Object getUserinfo(Authentication authentication) {
        Map<String, Object> userinfo = new HashMap<>();

        userinfo.put("name", authentication.getPrincipal());

        return userinfo;
    }
}
