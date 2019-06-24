package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entitiy.OauthClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailsRepository extends BaseRepository<OauthClientDetails,String> {
}
