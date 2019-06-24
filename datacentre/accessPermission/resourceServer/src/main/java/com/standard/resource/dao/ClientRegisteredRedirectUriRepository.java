package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entitiy.ClientRegisteredRedirectUri;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRegisteredRedirectUriRepository extends BaseRepository<ClientRegisteredRedirectUri,Long> {
}
