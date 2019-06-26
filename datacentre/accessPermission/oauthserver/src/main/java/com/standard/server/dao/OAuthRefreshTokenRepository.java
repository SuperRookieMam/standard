package com.standard.server.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.server.entity.OAuthRefreshToken;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRefreshTokenRepository extends BaseRepository<OAuthRefreshToken,Long> {
}
