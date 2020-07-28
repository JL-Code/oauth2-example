package org.example.oauth2.security.client;

import lombok.var;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * <p>创建时间: 2020/7/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Service
public class OAuth2ClientDetailsService implements ClientDetailsService, InitializingBean {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<OAuth2ClientDetails> clientDetails = new ArrayList<>();

    /**
     * Load a client by the client id. This method must not return null.
     *
     * @param clientId The client id.
     * @return The client details (never null).
     * @throws ClientRegistrationException If the client account is locked, expired, disabled, or invalid for any other reason.
     */
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        // TODO: 查询 clientDetails 模拟数据
        OAuth2ClientDetails client = new OAuth2ClientDetails();
        Optional<OAuth2ClientDetails> result = clientDetails.stream().filter(c -> c.getClientId().equalsIgnoreCase(clientId)).findFirst();
        if (result.isPresent()) {
            client = result.get();
        }
        return client;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        initClientDetails();
    }

    private void initClientDetails() {
        var corpAppConfig = new AppConfig("cdb", "ww13e9492fe4b02153", AppType.CORP_WECHAT_APP, 1000002, null,
                "dsq1HWzRkcJ27VxrC8gRsTXu17Kq39LlJIY5kk3w-g4");
        var dingtalkScanCodeAppConfig = new AppConfig("cdb", "dingdf75c1727056d6a635c2f4657eb6378f",
                AppType.DINGTALK_SCANCODE_APP, null, "dingoakmulh1ut9nyk6kx4",
                "U0U-9P9FA1iXl-OljiVJkd_MKxnOLiF6LZ03KGccxdmrwGDevS-hKB3NrEfwgujr");

        var client = new OAuth2ClientDetails();
        client.setClientId("cdb");
        // spring security 设置了 PasswordEncoder 后，secret 需要用 passwordEncoder 编码后才能正确比对。
        client.setClientSecret(passwordEncoder.encode("cdb"));
        client.setScope(Arrays.asList("all"));
        client.setAuthorizedGrantTypes(Arrays.asList("password", "client_credentials", "refresh_token",
                "authorization_code"));

        client.setAppConfigs(Arrays.asList(corpAppConfig, dingtalkScanCodeAppConfig));

        clientDetails.add(client);
    }
}
