package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entity.ResourceServer;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceServerRepository extends BaseRepository<ResourceServer,String> {
}
