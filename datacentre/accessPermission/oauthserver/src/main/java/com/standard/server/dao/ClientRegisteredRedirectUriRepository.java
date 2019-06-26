package com.standard.server.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.server.entity.ClientRegisteredRedirectUri;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRegisteredRedirectUriRepository extends BaseRepository<ClientRegisteredRedirectUri,Long> {
}
