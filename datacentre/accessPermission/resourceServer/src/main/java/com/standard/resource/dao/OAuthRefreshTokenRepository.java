package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entitiy.OAuthRefreshToken;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRefreshTokenRepository extends BaseRepository<OAuthRefreshToken,Long> {
}
