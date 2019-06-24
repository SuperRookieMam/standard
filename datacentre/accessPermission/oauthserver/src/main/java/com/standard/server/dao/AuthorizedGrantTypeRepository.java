package com.standard.server.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.server.entitiy.AuthorizedGrantType;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizedGrantTypeRepository extends BaseRepository<AuthorizedGrantType,Long> {
}
