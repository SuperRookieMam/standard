package com.standard.permission.componet.feature;

import com.standard.oauthCommon.dto.AccessTokenDto;
import com.standard.oauthCommon.dto.RefreshTokenDto;
import com.standard.oauthCommon.utils.SerializationUtils;
import com.standard.permission.cover.OAuthAccessTokenCover;
import com.standard.permission.cover.RefreshTokenCover;
import com.standard.permission.entity.OAuthAccessToken;
import com.standard.permission.entity.OAuthRefreshToken;
import com.standard.permission.service.OAuthAccessTokenService;
import com.standard.permission.service.OAuthRefreshTokenService;
import lombok.Setter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;


@Setter
public class TokenStoreCover implements TokenStore {

    private OAuthAccessTokenService oAuthAccessTokenService;

    private OAuthRefreshTokenService refreshTokenService;

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();
    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
         OAuthAccessToken oAuthAccessToken = oAuthAccessTokenService.findById(token);
         if (!ObjectUtils.isEmpty(oAuthAccessToken))
            return oAuthAccessToken.getAuthentication();
        return null;
    }

    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        OAuthAccessToken  accessToken = oAuthAccessTokenService.findById(token.getValue());
        AccessTokenDto dto =(AccessTokenDto)token;
        // 为保证 token的一直性，这里还是删除一下的好
        if (!ObjectUtils.isEmpty(accessToken)){
            accessToken.setAuthenticationId(dto.getAuthenticationId());
            accessToken.setAuthentication(dto.getAuthentication());
            accessToken.setClientId(dto.getClientId());
//        这里序列化存的是 refreshTokendto
            accessToken.setRefreshToken(dto.getRefreshToken());
            accessToken.setTokenType(OAuth2AccessToken.BEARER_TYPE.toLowerCase());
            accessToken.setScope(dto.getScope());
            accessToken.setUserName(dto.getUserName());
            accessToken.setExpiration(token.getExpiration());
            oAuthAccessTokenService.updateByEntity(accessToken);
        }else {
            accessToken =new OAuthAccessToken();
            accessToken.setAuthenticationId(dto.getAuthenticationId());
            accessToken.setAuthentication(dto.getAuthentication());
            accessToken.setClientId(dto.getClientId());
            //        这里序列化存的是 refreshTokendto
            accessToken.setRefreshToken(dto.getRefreshToken());
            accessToken.setTokenType(OAuth2AccessToken.BEARER_TYPE.toLowerCase());
            accessToken.setScope(dto.getScope());
            accessToken.setUserName(dto.getUserName());
            accessToken.setExpiration(token.getExpiration());
            oAuthAccessTokenService.insertByEntity(accessToken);
        }
        dto.setTokenId(accessToken.getTokenId());
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuthAccessToken authAccessToken =  oAuthAccessTokenService.findById(tokenValue);
        return authAccessToken==null?null:OAuthAccessTokenCover.toDto(authAccessToken);
    }

    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public void removeAccessToken(OAuth2AccessToken token) {
        oAuthAccessTokenService.deletById(token.getValue());
    }

    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        RefreshTokenDto dto =(RefreshTokenDto)refreshToken;
        List<OAuthRefreshToken> list = refreshTokenService.findByTokenId(dto.getValue());
        if (!list.isEmpty()){
            OAuthRefreshToken oAuthRefreshToken =list.get(0);
            oAuthRefreshToken.setAuthentication(dto.getAuthentication());
            oAuthRefreshToken.setTokenId(dto.getValue());
            refreshTokenService.updateByEntity(oAuthRefreshToken);
        }else {
            OAuthRefreshToken oAuthRefreshToken =new OAuthRefreshToken();
            oAuthRefreshToken.setAuthentication(dto.getAuthentication());
            oAuthRefreshToken.setTokenId(dto.getValue());
            refreshTokenService.insertByEntity(oAuthRefreshToken);
        }
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String tokenValue) {
      List<OAuthRefreshToken> list =  refreshTokenService.findByTokenId(tokenValue);
        return list.isEmpty()?null: RefreshTokenCover.toDto(list.get(0));
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        OAuth2RefreshToken refreshToken =  readRefreshToken(token.getValue());
        if(!ObjectUtils.isEmpty(refreshToken)){
            return  ((RefreshTokenDto) refreshToken).getAuthentication();
        }
        return null;
    }

    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public void removeRefreshToken(OAuth2RefreshToken token) {
        refreshTokenService.deleteByTokenId(token.getValue());
    }

    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        oAuthAccessTokenService.deleteByAuthenticationId(refreshToken.getValue());
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        String authenticationId =authenticationKeyGenerator.extractKey(authentication);
        OAuthAccessToken oAuthAccessToken =oAuthAccessTokenService.findByAuthenticationId(authenticationId);
        if (!ObjectUtils.isEmpty(oAuthAccessToken))
                return OAuthAccessTokenCover.toDto(oAuthAccessToken);
        return null;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientIdAndUserName(String clientId, String userName) {
        return oAuthAccessTokenService.findByClientAndUername(clientId,userName)
                                         .stream()
                                         .map(ele-> OAuthAccessTokenCover.toDto(ele))
                                         .collect(Collectors.toList());
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        return oAuthAccessTokenService.findByClient(clientId)
                .stream()
                .map(ele-> OAuthAccessTokenCover.toDto(ele))
                .collect(Collectors.toList());
    }
}
