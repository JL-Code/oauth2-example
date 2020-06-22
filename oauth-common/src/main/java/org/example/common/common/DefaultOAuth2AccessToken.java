package org.example.common.common;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>描述: 默认的 OAuth2 访问令牌模型 </p>
 * <p>创建时间: 2020/6/22 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class) // 必须添加对下划线属性的转换支持 不然通过restTemplate.postForEntity
// 时会报转换异常 解决方案参考：https://www.codenong.com/10519265/
public class DefaultOAuth2AccessToken implements Serializable {
    //    @JsonProperty("access_token")
    private String accessToken;
    //    @JsonProperty("token_type")
    private String tokenType;
    //    @JsonProperty("refresh_token")
    private String refreshToken;
    //    @JsonProperty("expires_in")
    private int expiresIn;
    private String scope;

}