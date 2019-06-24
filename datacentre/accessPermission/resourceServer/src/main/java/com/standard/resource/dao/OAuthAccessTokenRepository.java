package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entitiy.OAuthAccessToken;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthAccessTokenRepository extends BaseRepository<OAuthAccessToken,String> {
}
