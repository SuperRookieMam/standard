package com.standard.resource.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.oauthCommon.dto.UserDetailsDto;
import com.standard.orm.componet.feature.DynamicTypeSelect;
import com.standard.resource.entitiy.OAthUserDetailes;
import com.standard.resource.service.OAthUserDetailesService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Service
public class OAthUserDetailesServiceImpl extends BaseServiceImpl<OAthUserDetailes, Long> implements OAthUserDetailesService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        OAthUserDetailes oAthUserDetailes = findByUsername(username);
        if (ObjectUtils.isEmpty(oAthUserDetailes)){
            throw new UsernameNotFoundException("用户不存在");
        }
        return UserDetailsDto.toDto(oAthUserDetailes);
    }
    @Override
    public OAthUserDetailes  findByUsername(String username){
       DynamicTypeSelect dynamicTypeSelect = getBaseRepository().getDynamicTypeSelect();
        dynamicTypeSelect.dynamicBuild(ele->{
            Predicate predicate = ele.flat.addEq("username",username, JoinType.LEFT).and();
            ele.query.where(predicate);
            return predicate;
        });
        List<OAthUserDetailes> list =dynamicTypeSelect.getResult();
        return list.size()>0?list.get(0):null;
    }
}
