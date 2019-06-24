package com.standard.server.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.server.entitiy.OauthClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailsRepository extends BaseRepository<OauthClientDetails,String> {
}
