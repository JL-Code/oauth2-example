package org.example.resource2.service;

import feign.Param;
import feign.RequestLine;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/7/6 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
public interface ResourceService {
    @RequestLine("GET /resource/info/{id}")
    public String getResource(@Param("id") String id);
}
