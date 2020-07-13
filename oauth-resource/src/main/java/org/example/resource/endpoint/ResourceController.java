package org.example.resource.endpoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
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
    public String getResource(@PathVariable String id, Authentication principal) {
        return "name:" + principal.getName() + "id:" + id;
    }

    @GetMapping("/resource/detail/{id}")
    public Resource getResourceDetail(@PathVariable String id, Principal principal) {
        return new Resource(id, principal.getName());
    }
}

@Data
@AllArgsConstructor
class Resource implements Serializable {
    private String id;
    private String name;
}