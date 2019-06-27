package com.standard.permission.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.permission.entity.OauthClientDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailsRepository extends BaseRepository<OauthClientDetails,String> {
}
