package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entitiy.ResourceServerClient;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceServerClientRepository extends BaseRepository<ResourceServerClient,String> {
}
