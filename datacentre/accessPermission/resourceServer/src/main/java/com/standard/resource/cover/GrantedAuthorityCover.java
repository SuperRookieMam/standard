package com.standard.resource.cover;

import com.standard.oauthCommon.dto.GrantedAuthorityDto;
import com.standard.resource.entity.OAthGrantedAuthority;

public class GrantedAuthorityCover  {

     public static GrantedAuthorityDto toDto(OAthGrantedAuthority grantedAuthority){
          GrantedAuthorityDto grantedAuthorityDto =new GrantedAuthorityDto();
          grantedAuthorityDto.setId(grantedAuthority.getId());
          grantedAuthorityDto.setResourceId(grantedAuthority.getResourceId());
          grantedAuthorityDto.setApiName(grantedAuthority.getApiName());
          grantedAuthorityDto.setApiDescription(grantedAuthority.getApiDescription());
          grantedAuthorityDto.setApiUri(grantedAuthority.getApiUri());
          grantedAuthorityDto.setMethod(grantedAuthority.getMethod());
          grantedAuthorityDto.setMactherType(grantedAuthority.getMactherType());
          grantedAuthorityDto.setRoleId(grantedAuthority.getRoleId());
          grantedAuthorityDto.setRoleName(grantedAuthority.getRoleName());
          grantedAuthorityDto.setScope(grantedAuthority.getScope());
          return grantedAuthorityDto;
     }
}
