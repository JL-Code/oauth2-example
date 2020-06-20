package org.example.oauth2.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/18 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
@RequestMapping("/app")
public class AppController {

    @RequestMapping("/public")
    public String hello() {
        return "public";
    }

    @RequestMapping("/protected")
    public String hello2() {
        return "protected";
    }
}
