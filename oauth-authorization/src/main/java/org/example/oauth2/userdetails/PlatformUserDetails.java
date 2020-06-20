package org.example.oauth2.userdetails;

import lombok.Data;

/**
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2020/6/18 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class PlatformUserDetails extends AbstractUserDetails {

    public PlatformUserDetails() {
        this.setEnabled(true);
        this.setAccountNonLocked(true);
        this.setAccountNonExpired(true);
        this.setCredentialsNonExpired(true);
    }

    private String username;

    @Override
    public String getUsername() {
        return username;
    }
}
