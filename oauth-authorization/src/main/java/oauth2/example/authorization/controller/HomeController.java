package oauth2.example.authorization.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>创建时间: 2021/1/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String methodName() {
        return ":";
    }
}
