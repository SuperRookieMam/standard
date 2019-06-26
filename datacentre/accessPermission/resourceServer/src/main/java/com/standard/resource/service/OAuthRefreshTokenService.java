package com.standard.resource.service;

import com.standard.base.service.BaseService;
import com.standard.resource.entity.OAuthRefreshToken;

import java.util.List;

public interface OAuthRefreshTokenService extends BaseService<OAuthRefreshToken, Long> {

    int  deleteByTokenId(String tokenId);

    List<OAuthRefreshToken> findByTokenId(String tokenId);
}
