package com.standard.oauthCommon.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.io.Serializable;

@Getter
@Setter
public class RefreshTokenDto implements OAuth2RefreshToken, Serializable {

    private static final long serialVersionUID = 5811202205975164945L;
    private Long id;

    private String tokenId;

    private OAuth2Authentication authentication;
    @Override
    public String getValue() {
        return tokenId;
    }

}
