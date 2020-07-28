package org.example.oauth2.security.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.List;

/**
 * <p>创建时间: 2020/7/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Getter
@Setter
public class OAuth2ClientDetails extends BaseClientDetails {

    private List<AppConfig> appConfigs;

    public DingtalkApp getDingtalkApp() {
        DingtalkApp app = null;
        for (AppConfig config : appConfigs) {
            if (config.getAppType() == AppType.DINGTALK_SCANCODE_APP && config.getClientId().equalsIgnoreCase(this.getClientId())) {
                app = new DingtalkApp(config.getCorpId(), config.getAppKey(), config.getAppSecret());
            }
        }
        return app;
    }

    public CorpWeChatApp getCorpWeChatApp() {
        CorpWeChatApp app = null;
        for (AppConfig config : appConfigs) {
            if (config.getAppType() == AppType.CORP_WECHAT_APP && config.getClientId().equalsIgnoreCase(this.getClientId())) {
                app = new CorpWeChatApp(config.getCorpId(), config.getAgentId(), config.getAppSecret());
            }
        }
        return app;
    }

    @Data
    @AllArgsConstructor
    public class DingtalkApp {
        private String corpId;
        private String appId;
        private String appSecret;
    }

    @Data
    @AllArgsConstructor
    public class CorpWeChatApp {
        private String corpId;
        private Integer agentId;
        private String appSecret;
    }
}
