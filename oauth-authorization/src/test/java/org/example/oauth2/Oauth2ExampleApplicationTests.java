package org.example.oauth2;

import org.example.oauth2.dao.UserDao;
import org.example.oauth2.entity.User;
import org.example.oauth2.userdetails.UaaUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.annotation.Resource;
import java.util.List;

/**
 * @see "https://juejin.im/post/5d80c66f51882539aa5adc10#heading-8"
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class Oauth2ExampleApplicationTests {

    @Resource
    private UserDao userDao;
    @Autowired
    private UaaUserDetailsService platformUserDetailsService;

    @Test
    void contextLoads() {
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userDao.selectList(null);
        Assertions.assertEquals(43, userList.size());
        userList.forEach(System.out::println);
//        platformUserDetailsService.loadUserByUsername("admin");
    }

    @Test
    public void testUpdate() {

    }
}
