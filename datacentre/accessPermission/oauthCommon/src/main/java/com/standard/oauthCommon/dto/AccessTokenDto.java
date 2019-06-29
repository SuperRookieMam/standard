package com.standard.oauthCommon.dto;

import com.standard.oauthCommon.utils.SerializationUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.io.Serializable;
import java.util.*;

@Getter
@Setter
public class AccessTokenDto implements OAuth2AccessToken ,Serializable{
    private static final long serialVersionUID = 6426419878090803314L;

    private String tokenId;

    private String authenticationId;

    private OAuth2Authentication authentication;

    private String clientId;

    private RefreshTokenDto refreshToken;

    private String tokenType;

    private Set<String> scope=new HashSet<>();

    private Date expiration;

    private String userName;

    private Map<String,Object> additionalInformation=new HashMap<>();
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
