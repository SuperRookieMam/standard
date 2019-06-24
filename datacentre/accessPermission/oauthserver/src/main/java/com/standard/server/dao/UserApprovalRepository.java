package com.standard.server.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.server.entitiy.UserApproval;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApprovalRepository extends BaseRepository<UserApproval,Long> {
}
