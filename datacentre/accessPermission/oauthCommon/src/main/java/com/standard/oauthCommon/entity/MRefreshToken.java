package com.standard.oauthCommon.entity;

import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

public interface MRefreshToken extends OAuth2RefreshToken, Serializable {
    Long getId();

    String getTokenId();

    String getAuthentication();
}
