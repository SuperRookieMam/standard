package com.standard.server.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.server.entity.OAuthClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientDetailsRepository extends BaseRepository<OAuthClientDetails,String> {
}
