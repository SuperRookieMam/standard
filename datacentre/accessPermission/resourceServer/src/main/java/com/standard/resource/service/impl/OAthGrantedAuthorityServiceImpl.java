package com.standard.resource.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.orm.componet.feature.DynamicTypeSelect;
import com.standard.resource.entitiy.OAthGrantedAuthority;
import com.standard.resource.service.OAthGrantedAuthorityService;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class OAthGrantedAuthorityServiceImpl extends BaseServiceImpl<OAthGrantedAuthority, Long> implements OAthGrantedAuthorityService {
    @Override
    public List<OAthGrantedAuthority> getAuthorityByMethodAndResourceId(String method,String resourceId){
            DynamicTypeSelect dynamicTypeSelect =getBaseRepository().getDynamicTypeSelect();
                dynamicTypeSelect.dynamicBuild(ele ->{
                  Predicate predicate = ele.flat.addEq("method",method, JoinType.LEFT)
                                                .addEq("resourceId",resourceId,JoinType.LEFT)
                                                .and();
                    ele.query.where(predicate);
                  return predicate;
                });

        return dynamicTypeSelect.getResult();
    }

}
