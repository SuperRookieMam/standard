package com.standard.resource.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.orm.componet.feature.DynamicDelete;
import com.standard.orm.componet.feature.DynamicTypeSelect;
import com.standard.resource.entity.OAuthAccessToken;
import com.standard.resource.service.OAuthAccessTokenService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class OAuthAccessTokenServiceImpl extends BaseServiceImpl<OAuthAccessToken, String> implements OAuthAccessTokenService {

    @Override
    @Transactional(value = "jpaTransactionManager", rollbackFor = Exception.class)
    public int  deleteByAuthenticationId(String authenticationId){
          DynamicDelete dynamicDelete = getBaseRepository().getDynamicDelete();
        dynamicDelete.dynamicBuild(ele ->{
            Predicate predicate = ele.flat.addEq("authenticationId",authenticationId, JoinType.LEFT).and();
            ele.delete.where(predicate);
            return predicate;
        });
        return   dynamicDelete.getResult();
    }
    @Override
    public  OAuthAccessToken findByAuthenticationId(String authenticationId){
       DynamicTypeSelect dynamicTypeSelect =getBaseRepository().getDynamicTypeSelect();
       dynamicTypeSelect.dynamicBuild(ele ->{
           Predicate predicate = ele.flat.addEq("authenticationId",authenticationId, JoinType.LEFT).and();
           ele.query.where(predicate);
           return predicate;
       });
       List<OAuthAccessToken> list =dynamicTypeSelect.getResult();
        if (!list.isEmpty()){
           return list.get(0);
        }
        return null;
    }

    @Override
    public List<OAuthAccessToken> findByClientAndUername(String clientId, String userName) {
        DynamicTypeSelect dynamicTypeSelect =getBaseRepository().getDynamicTypeSelect();
        dynamicTypeSelect.dynamicBuild(ele->{
            Predicate predicate =   ele.flat.addEq("clientId",clientId, JoinType.LEFT)
                                    .addEq("userName",userName, JoinType.LEFT).and();
            ele.query.where(predicate);
            return predicate;
        });
        return dynamicTypeSelect.getResult();
    }
    @Override
    public List<OAuthAccessToken> findByClient(String clientId) {
        DynamicTypeSelect dynamicTypeSelect =getBaseRepository().getDynamicTypeSelect();
        dynamicTypeSelect.dynamicBuild(ele->{
            Predicate predicate =   ele.flat.addEq("clientId",clientId, JoinType.LEFT).and();
            ele.query.where(predicate);
            return predicate;
        });
        return dynamicTypeSelect.getResult();
    }
}
