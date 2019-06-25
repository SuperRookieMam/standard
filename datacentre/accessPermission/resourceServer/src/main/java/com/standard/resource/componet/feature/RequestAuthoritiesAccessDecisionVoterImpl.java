package com.standard.resource.componet.feature;

import com.standard.resource.entitiy.OAthUserDetailes;
import com.standard.resource.entitiy.UserRole;
import com.standard.resource.service.OAthUserDetailesService;
import com.standard.resource.service.UserRoleService;
import com.standard.securityCommon.access.RequestAuthoritiesAccessDecisionVoter;
import com.standard.securityCommon.access.RequestAuthorityAttribute;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Setter
@Component
public class RequestAuthoritiesAccessDecisionVoterImpl implements RequestAuthoritiesAccessDecisionVoter {
    @Autowired
    private OAthUserDetailesService oAthUserDetailesService;
    @Autowired
    private UserRoleService userRoleService;
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return attribute instanceof RequestAuthorityAttribute;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    /**
     */
    @Override
    public int vote(Authentication authentication, FilterInvocation object, Collection<ConfigAttribute> attributes) {
            if (attributes.isEmpty()){
                return ACCESS_GRANTED;
            }else {
               String userName =getUserName(authentication);
               OAthUserDetailes oAthUserDetailes = oAthUserDetailesService.findByUsername(userName);
               if (ObjectUtils.isEmpty(oAthUserDetailes)){
                   return   ACCESS_DENIED ;
                }
               List<UserRole> list = userRoleService.getUserRoleByUsername(userName);
                if (list.isEmpty()){
                    return   ACCESS_DENIED ;
                }
               String roleId =list.get(0).getRoleId().toString();
               Iterator<ConfigAttribute> iterator = attributes.iterator();
               while (iterator.hasNext()){
                 RequestAuthorityAttribute requestAuthorityAttribute =(RequestAuthorityAttribute)iterator.next();
                 if (roleId.equals(requestAuthorityAttribute.getRoleId())){
                     return ACCESS_GRANTED;
                 }
               }
            }
         return   ACCESS_DENIED ;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
       Assert.isNull(oAthUserDetailesService, "oAthUserDetailesService are required");
       Assert.isNull(userRoleService, "userRoleService are required");
    }

    private String getUserName(Authentication authentication){
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails){
            return   ((UserDetails) principal).getUsername();
        }else{
            return  ((Principal) principal).getName();
        }
    }
}
