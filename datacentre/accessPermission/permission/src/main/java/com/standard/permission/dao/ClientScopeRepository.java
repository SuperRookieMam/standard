package com.standard.permission.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.permission.entity.ClientScope;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientScopeRepository extends BaseRepository<ClientScope,Long> {
}
