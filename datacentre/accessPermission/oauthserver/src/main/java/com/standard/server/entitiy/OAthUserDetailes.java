package com.standard.server.entitiy;

import com.standard.base.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "oath_user_detailes",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name"})},
        indexes = {@Index(columnList = "user_name")})
public class OAthUserDetailes extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = 9056596580975978130L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    private String passWord;

    @Column(name = "head_image")
    private String headImage;

    // 用户的权限
    @Transient// 不建表，动态获取
    private List<OAthGrantedAuthority> authorities = Collections.emptyList();

    @Column(name = "is_expired")
    private boolean isExpired;

    @Column(name = "is_lock")
    private boolean isLock;

    @Column(name = "credentials")
    private String credentials;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }
}
