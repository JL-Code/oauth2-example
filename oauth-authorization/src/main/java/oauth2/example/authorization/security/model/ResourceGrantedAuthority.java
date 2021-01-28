package oauth2.example.authorization.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * <p>创建时间: 2021/1/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
@AllArgsConstructor
public class ResourceGrantedAuthority implements GrantedAuthority {
    private String authority;
}
