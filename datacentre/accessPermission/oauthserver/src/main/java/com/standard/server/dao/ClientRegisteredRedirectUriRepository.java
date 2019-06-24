package com.standard.server.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.server.entitiy.ClientRegisteredRedirectUri;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRegisteredRedirectUriRepository extends BaseRepository<ClientRegisteredRedirectUri,Long> {
}
