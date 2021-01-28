package oauth2.example.authorization.security.model;

import lombok.Data;
import lombok.ToString;
import oauth2.example.authorization.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * <p>创建时间: 2021/1/28 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class UserIdentity extends User implements UserDetails {
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private Collection<? extends GrantedAuthority> authorities;
}
