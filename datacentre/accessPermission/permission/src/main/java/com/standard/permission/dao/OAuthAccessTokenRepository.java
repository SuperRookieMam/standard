package com.standard.permission.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.permission.entity.OAuthAccessToken;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthAccessTokenRepository extends BaseRepository<OAuthAccessToken,String> {
}
