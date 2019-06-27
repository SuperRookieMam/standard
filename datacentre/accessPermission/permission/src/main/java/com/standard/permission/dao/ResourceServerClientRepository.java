package com.standard.permission.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.permission.entity.ResourceServerClient;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceServerClientRepository extends BaseRepository<ResourceServerClient,String> {
}
