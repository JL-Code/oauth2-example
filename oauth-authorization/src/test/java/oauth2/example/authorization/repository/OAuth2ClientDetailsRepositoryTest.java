package oauth2.example.authorization.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/1/30 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@SpringBootTest
class OAuth2ClientDetailsRepositoryTest {

    @Autowired
    OAuth2ClientDetailsRepository repository;

    @Test
    public void testAddClientDetails() {

//        BaseClientDetails clietnDetails = new BaseClientDetails();
//
//        clietnDetails.setClientId("baseClientDetails");
//
//        repository.insert(clietnDetails);
//
//        BaseClientDetails old = repository.selectById(clietnDetails.getClientId());
//
//        assertEquals("baseClientDetails", old.getClientId());
    }
}