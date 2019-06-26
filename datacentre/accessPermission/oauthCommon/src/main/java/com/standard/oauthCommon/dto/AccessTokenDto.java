package com.standard.oauthCommon.dto;

import com.standard.oauthCommon.utils.SerializationUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
public class AccessTokenDto implements OAuth2AccessToken ,Serializable{
    private static final long serialVersionUID = 6426419878090803314L;

    private String tokenId;

    private String token;

    private String authenticationId;

    private String authentication;

    private String clientId;

    private String refreshToken;

    private String tokenType;

    private Set<String> scope;

    private Date expiration;

    private String userName;

    private Map<String,Object> additionalInformation;
    @Override
    public OAuth2RefreshToken getRefreshToken() {
        RefreshTokenDto refreshTokenDto =null;
        if (refreshToken!=null)
            refreshTokenDto = SerializationUtils.deserialize(refreshToken);
        return refreshTokenDto;
    }
    @Override
    public boolean isExpired() {
        return expiration.getTime()<System.currentTimeMillis();
    }
    @Override
    public int getExpiresIn() {
        return expiration.getTime()<System.currentTimeMillis()
                ?0
                :(int)((System.currentTimeMillis()-expiration.getTime())/1000);
    }

    @Override
    public String getValue() {
        return tokenId;
    }
}
