package org.example.resource2.endpoint;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import org.example.resource2.Resource;
import org.example.resource2.service.ResourceService;
import org.example.resource2.service.TokenRequestInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class OpenFeignController {
    private final ResourceService service;

    public OpenFeignController() {
        // 手动创建 Feign Client @see https://github.com/OpenFeign/feign
        service = Feign.builder()
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .requestInterceptor(new TokenRequestInterceptor())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .target(ResourceService.class, "http://localhost:8081");
    }

    @GetMapping("/resource/info/{id}")
    public String getResource(@PathVariable String id) {
        ResourceService resourceService = Feign.builder()
                .logger(new Slf4jLogger())
                .logLevel(Logger.Level.FULL)
                .requestInterceptor(new TokenRequestInterceptor())
                // TODO:  为什么 JacksonDecoder 无法解码 Java 的简单类型。
//                .encoder(new JacksonEncoder())
//                .decoder(new JacksonDecoder())
                .target(ResourceService.class, "http://localhost:8081");
        String result = resourceService.getResource(id);
        return result;
    }

    @GetMapping("/resource/detail/{id}")
    public Resource getResourceDetail(@PathVariable String id) {
        return service.getResourceDetail(id);
    }
}
