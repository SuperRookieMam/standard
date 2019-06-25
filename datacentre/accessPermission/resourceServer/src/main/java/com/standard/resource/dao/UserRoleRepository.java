package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entitiy.UserRole;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends BaseRepository<UserRole,Long> {
}
