package org.example.common.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

/**
 * <p>创建时间: 2020/7/22 </p>
 *
 * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
 * @version v1.0
 */
@Data
public class UaaUserDetails extends AbstractUserDetails {

    private String id;
    private String username;
    private String userCode;
    private String password;
    private String phoneNumber;
    private String corpWeChatUserId;
    private String dingtalkUserId;
    private Collection<ResourceAuthority> authorities;

    public UaaUserDetails() {
        this.setEnabled(true);
        this.setAccountNonLocked(true);
        this.setAccountNonExpired(true);
        this.setCredentialsNonExpired(true);
    }
}
