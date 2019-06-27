package com.standard.permission.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.permission.entity.ClientRegisteredRedirectUri;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRegisteredRedirectUriRepository extends BaseRepository<ClientRegisteredRedirectUri,Long> {
}
