package com.standard.server.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.orm.componet.feature.DynamicTypeSelect;
import com.standard.server.entity.UserApproval;
import com.standard.server.service.UserApprovalService;
import org.springframework.security.oauth2.provider.approval.Approval;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserApprovalServiceImpl extends BaseServiceImpl<UserApproval, Long> implements UserApprovalService {
    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public boolean addApprovals(Collection<Approval> approvals) {
        approvals.stream().forEach(ele ->{
            UserApproval userApproval =new UserApproval();
            userApproval.setClientId(ele.getClientId());
            userApproval.setUserId(ele.getUserId());
            userApproval.setScope(ele.getScope());
            userApproval.setLastUpdatedAt(ele.getLastUpdatedAt());
            userApproval.setStatus(ele.getStatus());
            userApproval.setExpiresAt(ele.getExpiresAt());
            insertByEntity(userApproval);
        });
        return true;
    }

    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public boolean revokeApprovals(Collection<Approval> approvals) {
       List<UserApproval> list = approvals.stream().map(ele ->{
            UserApproval userApproval =new UserApproval();
            userApproval.setClientId(ele.getClientId());
            userApproval.setUserId(ele.getUserId());
            userApproval.setScope(ele.getScope());
            userApproval.setLastUpdatedAt(ele.getLastUpdatedAt());
            userApproval.setStatus(ele.getStatus());
            userApproval.setExpiresAt(ele.getExpiresAt());
            return userApproval;
        }).collect(Collectors.toList());
        deletByEntitys(list);
        return true;
    }

    @Override
    public Collection<Approval> getApprovals(String userId, String clientId) {
        return findByUserIdAndClientId(userId,clientId).stream().map(ele ->{
            return new Approval(ele.getUserId(),
                                 ele.getClientId(),
                                 ele.getScope(),
                                 ele.getExpiresAt(),
                                 ele.getStatus(),
                                 ele.getLastUpdatedAt());
                                 }).collect(Collectors.toList());
    }

    @Override
    public List<UserApproval> findByUserIdAndClientId(String userId, String clientId){
        DynamicTypeSelect dynamicTypeSelect = getBaseRepository().getDynamicTypeSelect();
        dynamicTypeSelect.dynamicBuild(ele ->{
           Predicate predicate = ele.flat.addEq("userId",userId, JoinType.LEFT)
                                          .addEq("clientId",clientId,JoinType.LEFT).and();

            return predicate;
        });
        return dynamicTypeSelect.getResult();
    }
}
