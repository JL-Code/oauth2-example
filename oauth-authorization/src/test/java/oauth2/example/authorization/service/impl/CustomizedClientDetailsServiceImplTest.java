package oauth2.example.authorization.service.impl;

import oauth2.example.authorization.security.service.CustomizedClientDetailsService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.util.collections.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * <p>创建时间: 2021/1/30 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@SpringBootTest
class CustomizedClientDetailsServiceImplTest {

    @Autowired
    CustomizedClientDetailsService service;

    @Test
    void loadClientByClientId() {
        ClientDetails clientDetails = service.loadClientByClientId("addedClientIdWithNoDetails");
        System.out.println(clientDetails);

        assertNotNull(clientDetails);
        assertEquals("addedClientIdWithNoDetails", clientDetails.getClientId());
    }

    @Test
    void addClientDetails() {
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails.setClientId("addedClientIdWithNoDetails");
        clientDetails.setClientSecret("clientSecret");
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code", "refresh_token"));
        clientDetails.setScope(Arrays.asList("snsapi_base", "snsapi_userinfo"));
        clientDetails.setAutoApproveScopes(Arrays.asList("snsapi_base", "snsapi_userinfo"));
        clientDetails.setRegisteredRedirectUri(Sets.newSet("http://localhost:8081", "http://localhost:8082"));

        service.addClientDetails(clientDetails);

        ClientDetails result = service.loadClientByClientId("addedClientIdWithNoDetails");

        assertNotNull(result);
    }

    @Test
    void updateClientDetails() {
    }

    @Test
    void deleteByClientId() {
    }
}