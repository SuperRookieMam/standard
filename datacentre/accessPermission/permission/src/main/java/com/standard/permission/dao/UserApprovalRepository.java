package com.standard.permission.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.permission.entity.UserApproval;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApprovalRepository extends BaseRepository<UserApproval,Long> {
}
