package org.example.resource.endpoint;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/19 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@RestController
public class ResourceController {

    @GetMapping("/resource/info/{id}")
    public String getResource(@PathVariable String id, Principal principal) {
        return "name:" + principal.getName() + "id:" + id;
    }
}
