package com.standard.server.entitiy;

import com.standard.base.entity.BaseEntity;
import com.standard.codecreate.feature.annotation.IsCreate;
import com.standard.oauthCommon.entity.MUserDetails;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@IsCreate
@Table(name = "oath_user_detailes_",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_name_"})},
        indexes = {@Index(columnList = "user_name_")})
public class OAthUserDetailes extends BaseEntity implements MUserDetails {

    private static final long serialVersionUID = 9056596580975978130L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_")
    private Long id;

    @Column(name = "user_name_")
    private String username;

    @Column(name = "pass_word_")
    private String password;

    @Column(name = "head_image_")
    private String headImage;

    // 用户的权限
    @Transient// 不建表，动态获取
    private List<? extends GrantedAuthority> authorities;

    @Column(name = "expired_")
    private boolean expired;

    @Column(name = "lock_")
    private boolean lock;

    @Column(name = "credentials_")
    private String credentials;

    @Column(name = "is_enabled_")
    private boolean enabled;

    @Override
    public boolean isAccountNonExpired() {
        return expired;
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
