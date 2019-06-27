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

import java.util.Collection;
import java.util.List;
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
            return SerializationUtils.deserialize(oAuthAccessToken.getAuthentication());
        return null;
    }

    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        OAuthAccessToken  accessToken = oAuthAccessTokenService.findById(token.getValue());
        String authenticationId =authenticationKeyGenerator.extractKey(authentication);
        // 为保证 token的一直性，这里还是删除一下的好
        if (!ObjectUtils.isEmpty(accessToken)){
            AccessTokenDto dto = OAuthAccessTokenCover.toDto(accessToken);
            oAuthAccessTokenService.deletById(token.getValue());
            //    这里反序列化出来就是refreshTokendto
            OAuth2RefreshToken refreshToken = dto.getRefreshToken();
            if (!ObjectUtils.isEmpty(refreshToken)){
                refreshTokenService.deleteByTokenId(refreshToken.getValue());
            }
        }
        RefreshTokenDto refreshTokenDto =  new RefreshTokenDto();
        refreshTokenDto.setTokenId(authenticationId);
        refreshTokenDto.setAuthentication(SerializationUtils.serialize(authentication));
        OAuthAccessToken  oAuthAccessToken =new OAuthAccessToken();
        oAuthAccessToken.setAuthenticationId(authenticationId);
        oAuthAccessToken.setAuthentication(SerializationUtils.serialize(authentication));
        oAuthAccessToken.setClientId(authentication.getOAuth2Request().getClientId());
//        这里序列化存的是 refreshTokendto
        oAuthAccessToken.setRefreshToken(SerializationUtils.serialize(refreshTokenDto));
        oAuthAccessToken.setTokenType(OAuth2AccessToken.BEARER_TYPE.toLowerCase());
        oAuthAccessToken.setScope(authentication.getOAuth2Request().getScope());
        oAuthAccessToken.setUserName(authentication.isClientOnly() ? null : authentication.getName());
        oAuthAccessToken.setExpiration(token.getExpiration());
        oAuthAccessTokenService.insertByEntity(oAuthAccessToken);
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
        String authenticationId =authenticationKeyGenerator.extractKey(authentication);
        List<OAuthRefreshToken> list = refreshTokenService.findByTokenId(authenticationId);
        if (!list.isEmpty()){
            refreshTokenService.deleteByTokenId(authenticationId);
        }
        OAuthRefreshToken oAuthRefreshToken =new OAuthRefreshToken();
        oAuthRefreshToken.setAuthentication(SerializationUtils.serialize(authentication));
        oAuthRefreshToken.setTokenId(authenticationId);
        refreshTokenService.insertByEntity(oAuthRefreshToken);
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
            return  SerializationUtils.deserialize (((RefreshTokenDto) refreshToken).getAuthentication()) ;
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
