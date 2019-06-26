package com.standard.resource.service;

import com.standard.base.service.BaseService;
import com.standard.resource.entity.OAuthAccessToken;

import java.util.List;

public interface OAuthAccessTokenService extends BaseService<OAuthAccessToken, String> {

    int  deleteByAuthenticationId(String authenticationId);

    OAuthAccessToken findByAuthenticationId(String authenticationId);

    List<OAuthAccessToken> findByClientAndUername(String clientId, String userName);

    List<OAuthAccessToken> findByClient(String clientId);
}
