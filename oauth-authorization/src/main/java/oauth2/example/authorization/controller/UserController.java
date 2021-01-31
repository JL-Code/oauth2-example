package oauth2.example.authorization.controller;

import oauth2.example.authorization.entity.User;
import oauth2.example.authorization.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>创建时间: 2021/1/29 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户信息
     *
     * @param openId
     * @return
     */
    @GetMapping("/oauth/userinfo")
    public User getUserInfo(String openId) {
        User user = userService.getById(openId);
        return user;
    }
}
