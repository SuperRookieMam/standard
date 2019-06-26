package com.standard.server.service;

import com.standard.base.service.BaseService;
import com.standard.server.entity.UserApproval;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;

import java.util.List;

public interface UserApprovalService extends BaseService<UserApproval, Long>,ApprovalStore {

    List findByUserIdAndClientId(String userId, String clientId);
}
