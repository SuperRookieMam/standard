package com.standard.server.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.server.entity.OAuthAccessToken;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthAccessTokenRepository extends BaseRepository<OAuthAccessToken,String> {
}
