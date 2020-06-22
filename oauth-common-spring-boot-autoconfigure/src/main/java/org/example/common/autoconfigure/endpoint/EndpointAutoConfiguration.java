package org.example.common.autoconfigure.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/22 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Configuration
@ComponentScan("org.example.common.endpoint")
public class EndpointAutoConfiguration {
}
