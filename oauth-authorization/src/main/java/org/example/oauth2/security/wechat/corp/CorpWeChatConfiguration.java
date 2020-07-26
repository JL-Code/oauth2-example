package org.example.oauth2.security.wechat.corp;

import com.google.common.collect.Maps;
import lombok.val;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableConfigurationProperties(CorpWeChatProperties.class)
public class CorpWeChatConfiguration {

    private CorpWeChatProperties properties;
    private static Map<Integer, WxCpService> corpServices = Maps.newHashMap();

    public CorpWeChatConfiguration(CorpWeChatProperties properties) {
        Assert.state(properties.getCorpId() != null, "Corp WeChat getCorpId must be provided");
        Assert.state(properties.getAppConfigs() != null, "Corp WeChat getAppConfigs must be provided");
        this.properties = properties;
    }

    /**
     * 按照 agentId分组，初始企业微信服务。
     */
    @PostConstruct
    public void initServices() {
        corpServices = this.properties.getAppConfigs().stream().map(a -> {
            val configStorage = new WxCpDefaultConfigImpl();
            configStorage.setCorpId(this.properties.getCorpId());
            configStorage.setAgentId(a.getAgentId());
            configStorage.setCorpSecret(a.getSecret());
            configStorage.setToken(a.getToken());
            configStorage.setAesKey(a.getAesKey());
            val service = new WxCpServiceImpl();
            service.setWxCpConfigStorage(configStorage);
            return service;
        }).collect(Collectors.toMap(service -> service.getWxCpConfigStorage().getAgentId(), service -> service));
    }

    public static WxCpService getCorpService(Integer agentId) {
        return corpServices.get(agentId);
    }
}
