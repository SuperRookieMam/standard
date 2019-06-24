package com.standard.oauthCommon.entity;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public interface MOAuthAccessToken extends OAuth2AccessToken , Serializable {

     String getTokenId();

     String getToken();

     String getAuthenticationId();

     String getAuthentication();

     String getClientId();

     String getUserName();

}
