package org.example.common.model;

import lombok.Data;

import java.util.Collection;

/**
 * <p>创建时间: 2020/7/22 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class UaaUser extends AbstractUserDetails {

    private String id;
    private String username;
    private String userCode;
    private String password;
    private String phoneNumber;
    private Collection<ResourceAuthority> authorities;

    public UaaUser() {
        this.setEnabled(true);
        this.setAccountNonLocked(true);
        this.setAccountNonExpired(true);
        this.setCredentialsNonExpired(true);
    }
}
