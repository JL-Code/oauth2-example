package org.example.oauth2.security.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>创建时间: 2020/7/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppConfig {
    /**
     * 客户端 ID
     */
    private String clientId;
    /**
     * 企业ID
     */
    private String corpId;
    private AppType appType;
    /**
     * 通用应用ID字段
     */
    private Integer agentId;
    /**
     * 钉钉应用专属字段：小程序、H5微应用 appKey，当 appType 为扫码登录应用时，appKey=appId
     */
    private String appKey;
    private String appSecret;
}
