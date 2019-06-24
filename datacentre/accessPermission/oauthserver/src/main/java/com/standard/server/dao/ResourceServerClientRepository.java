package com.standard.server.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.server.entitiy.ResourceServerClient;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceServerClientRepository extends BaseRepository<ResourceServerClient,String> {
}
