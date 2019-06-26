package com.standard.oauthCommon.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

@Getter
@Setter
public class RefreshTokenDto implements OAuth2RefreshToken {

    private Long id;

    private String tokenId;

    private String authentication;
    @Override
    public String getValue() {
        return tokenId;
    }

}
