package oauth2.client.sample.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2021/2/8 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class IntegrationController {
    @GetMapping("/intergration/user")
    public Object getUser(Authentication authentication) {
        return authentication;
    }
}
