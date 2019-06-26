package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entity.AuthorizedGrantType;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizedGrantTypeRepository extends BaseRepository<AuthorizedGrantType,Long> {
}
