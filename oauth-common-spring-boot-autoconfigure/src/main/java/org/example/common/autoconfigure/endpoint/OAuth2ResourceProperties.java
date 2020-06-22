package org.example.common.autoconfigure.endpoint;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/22 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
@ConfigurationProperties(prefix = "oauth2-resource")
public class OAuth2ResourceProperties {

    private String clientId;
    private String clientSecret;
    private String tokenEndpoint;
    private String checkTokenEndpoint;
    private Boolean enabled;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getTokenEndpoint() {
        return tokenEndpoint;
    }

    public void setTokenEndpoint(String tokenEndpoint) {
        this.tokenEndpoint = tokenEndpoint;
    }

    public String getCheckTokenEndpoint() {
        return checkTokenEndpoint;
    }

    public void setCheckTokenEndpoint(String checkTokenEndpoint) {
        this.checkTokenEndpoint = checkTokenEndpoint;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
