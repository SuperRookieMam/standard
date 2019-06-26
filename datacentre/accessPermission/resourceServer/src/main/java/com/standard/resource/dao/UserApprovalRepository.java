package com.standard.resource.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.resource.entity.UserApproval;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApprovalRepository extends BaseRepository<UserApproval,Long> {
}
