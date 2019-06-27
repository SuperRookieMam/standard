package com.standard.oauthCommon.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Transient;
import java.util.List;

@Getter
@Setter
public class UserDetailsDto implements UserDetails {
    private static final long serialVersionUID = -2623233043628105246L;

    private Long id;

    private String username;

    private String password;

    private String headImage;

    // 用户的权限
    @Transient// 不建表，动态获取
    private List<? extends GrantedAuthority> authorities;

    private boolean expired;

    private boolean lock;

    private String credentials;

    private boolean enabled;
    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !lock;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !expired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
