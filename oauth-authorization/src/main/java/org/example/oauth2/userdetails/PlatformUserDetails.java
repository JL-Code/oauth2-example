//package org.example.oauth2.userdetails;
//
//import lombok.Data;
//import org.example.common.model.ResourceAuthority;
//
//import java.util.Collection;
//import java.util.HashMap;
//
///**
// * <p>描述: [类型描述] </p>
// * <p>创建时间: 2020/6/18 </p>
// *
// * @author <a href="mailto:jiangy@highzap.com" rel="nofollow">蒋勇</a>
// * @version v1.0
// */
//@Data
//public class PlatformUserDetails extends AbstractUserDetails {
//
//    public PlatformUserDetails() {
//        this.setEnabled(true);
//        this.setAccountNonLocked(true);
//        this.setAccountNonExpired(true);
//        this.setCredentialsNonExpired(true);
//    }
//
//    private String username;
//    /**
//     * 公司名称
//     */
//    private String companyName;
//    /**
//     * 部门名称
//     */
//    private String departmentName;
//    /**
//     * 用户拥有的权限集
//     */
//    private HashMap<String, String> authorities2;
//
//    private Collection<ResourceAuthority> authorities;
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public Collection<ResourceAuthority> getAuthorities() {
//        return authorities;
//    }
//}
