package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entity.OAuthRefreshToken;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRefreshTokenRepository extends BaseRepository<OAuthRefreshToken,Long> {
}
