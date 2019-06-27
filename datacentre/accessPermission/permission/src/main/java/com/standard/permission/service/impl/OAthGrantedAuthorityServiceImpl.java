package com.standard.permission.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.orm.componet.feature.DynamicTypeSelect;
import com.standard.permission.entity.OAthGrantedAuthority;
import com.standard.permission.service.OAthGrantedAuthorityService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class OAthGrantedAuthorityServiceImpl extends BaseServiceImpl<OAthGrantedAuthority, Long> implements OAthGrantedAuthorityService {
    @Override
    public List<OAthGrantedAuthority> getAuthorityByMethodAndResourceId(String method, String resourceId){
        DynamicTypeSelect dynamicTypeSelect =getBaseRepository().getDynamicTypeSelect();
        dynamicTypeSelect.dynamicBuild(ele ->{
            Predicate predicate = ele.flat.addEq("method", HttpMethod.valueOf(method), JoinType.LEFT)
                    .addEq("resourceId",resourceId,JoinType.LEFT)
                    .and();
            ele.query.where(predicate);
            return predicate;
        });

        return dynamicTypeSelect.getResult();
    }
}
