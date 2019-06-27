package com.standard.permission.cover;

import com.standard.oauthCommon.dto.RefreshTokenDto;
import com.standard.permission.entity.OAuthRefreshToken;

public class RefreshTokenCover {
    public  static RefreshTokenDto toDto(OAuthRefreshToken refreshToken){
        RefreshTokenDto refreshTokenDto =new RefreshTokenDto();
        refreshTokenDto.setAuthentication(refreshToken.getAuthentication());
        refreshTokenDto.setId(refreshToken.getId());
        refreshTokenDto.setTokenId(refreshToken.getTokenId());
        return refreshTokenDto;
    }
}
