package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entity.OauthClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthClientDetailsRepository extends BaseRepository<OauthClientDetails,String> {
}
