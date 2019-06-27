package com.standard.permission.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.permission.entity.OAuthRefreshToken;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRefreshTokenRepository extends BaseRepository<OAuthRefreshToken,Long> {
}
