package com.standard.oauthCommon.dto;

import com.standard.oauthCommon.entity.MRefreshToken;
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

    public  static<T extends MRefreshToken> RefreshTokenDto toDto(T refreshToken){
            RefreshTokenDto refreshTokenDto =new RefreshTokenDto();
            refreshTokenDto.setAuthentication(refreshToken.getAuthentication());
            refreshTokenDto.setId(refreshToken.getId());
            refreshTokenDto.setTokenId(refreshToken.getTokenId());
            return refreshTokenDto;
    }

}
