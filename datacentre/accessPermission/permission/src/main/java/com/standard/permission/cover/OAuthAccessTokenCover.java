package com.standard.permission.cover;

import com.standard.oauthCommon.dto.AccessTokenDto;
import com.standard.oauthCommon.utils.SerializationUtils;
import com.standard.permission.entity.OAuthAccessToken;

public class OAuthAccessTokenCover  {


     public static AccessTokenDto toDto(OAuthAccessToken accesstoken){
          AccessTokenDto accessTokenDto =new AccessTokenDto();
          accessTokenDto.setTokenId(accesstoken.getTokenId());
          accessTokenDto.setAuthenticationId(accesstoken.getAuthenticationId());
          accessTokenDto.setAuthentication(accesstoken.getAuthentication());
          accessTokenDto.setClientId(accesstoken.getClientId());
          accessTokenDto.setRefreshToken(accesstoken.getRefreshToken());
          accessTokenDto.setTokenType(accesstoken.getTokenType());
          accessTokenDto.setScope(accesstoken.getScope());
          accessTokenDto.setExpiration(accesstoken.getExpiration());
          accessTokenDto.setUserName(accesstoken.getUserName());
          accessTokenDto.setAdditionalInformation(accesstoken.getAdditionalInformation());
          return  accessTokenDto;
     }
}
