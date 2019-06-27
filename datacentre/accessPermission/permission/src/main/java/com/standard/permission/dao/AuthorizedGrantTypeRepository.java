package com.standard.permission.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.permission.entity.AuthorizedGrantType;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorizedGrantTypeRepository extends BaseRepository<AuthorizedGrantType,Long> {
}
