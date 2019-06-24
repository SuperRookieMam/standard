package com.standard.oauthCommon.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface MUserDetails extends UserDetails {
    Long getId();

    String getUsername();

    String getPassword();

    String getHeadImage();

    List<? extends GrantedAuthority> getAuthorities();

    boolean isExpired();

    boolean isLock();

    String getCredentials();

    boolean isEnabled();
}
