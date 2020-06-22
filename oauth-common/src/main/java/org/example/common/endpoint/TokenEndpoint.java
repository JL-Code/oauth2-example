package org.example.common.endpoint;

import org.example.common.common.DefaultOAuth2AccessToken;
import org.example.common.http.RESTEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * <p>描述: 令牌端点 </p>
 * <p>创建时间: 2020/6/22 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class TokenEndpoint {

    private RestOperations restTemplate;
    @Value("${oauth2-resource.token-endpoint}")
    private String tokenEndpoint;
    @Value("${oauth2-resource.client-id}")
    private String clientId;
    @Value("${oauth2-resource.client-secret}")
    private String clientSecret;

    public TokenEndpoint() {
        this.restTemplate = new RestTemplate();
        ((RestTemplate) restTemplate).setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            // Ignore 400
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() != 400) {
                    super.handleError(response);
                }
            }
        });
    }

    @PostMapping("/api/oauth/token")
    public ResponseEntity login(String username, String password) {

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("username", username);
        body.add("password", password);
        body.add("clientId", clientId);
        body.add("clientSecret", clientSecret);
        body.add("grant_type", "password");
        body.add("scope", "get_token");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // TODO: 期望将令牌请求逻辑封装成一个令牌请求方法，使用 OAuth2 规定的 HTTP 请求标准发送数据。
        String url = String.format("%s?username=%s&password=%s&grant_type=password&scope" +
                "=get_user_info" +
                "&client_id=%s&client_secret=%s", tokenEndpoint, username, password, clientId, clientSecret);
        ResponseEntity<DefaultOAuth2AccessToken> response = restTemplate.postForEntity(url,
                new HttpEntity<>(body, headers),
                DefaultOAuth2AccessToken.class);

        if (response.getStatusCodeValue() != 200) {
            // TODO: 期望直接从 restTemplate 中获取原始请求的错误响应信息来作为前端响应数据。
            return ResponseEntity.badRequest().body(new RESTEntity(400, "用户名或密码无效"));
        }

        return response;
    }
}
