package oauth2.example.authorization.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import oauth2.example.authorization.entity.User;
import oauth2.example.authorization.service.UserService;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

/**
 * <p>创建时间: 2021/1/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    void loadUserByUsername() throws JsonProcessingException {
        UserDetails userDetails = userService.loadUserByUsername("mecode");

        String jsonStr = new ObjectMapper().writeValueAsString(userDetails);

        System.out.println(jsonStr);

        Asserts.notNull(userDetails, "用户信息");
    }

    @Test
    public void testInsertUser() {
        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername("mecode");
        user.setPassword(passwordEncoder.encode("123"));
        user.setPhoneNumber("18580687918");
        boolean saved = userService.save(user);

        System.out.println(user);

        Asserts.check(saved, "保存成功");
    }

    @Test
    public void testBCryptPasswordEncoder() {
        String encodedPassword = new BCryptPasswordEncoder().encode("Cqhz.2020");
        System.out.println(encodedPassword);
    }
}