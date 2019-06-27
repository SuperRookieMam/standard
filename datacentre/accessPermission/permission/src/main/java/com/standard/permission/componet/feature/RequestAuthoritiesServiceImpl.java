package com.standard.permission.componet.feature;

import com.standard.permission.componet.constpackage.ConstParam;
import com.standard.permission.entity.OAthGrantedAuthority;
import com.standard.permission.service.OAthGrantedAuthorityService;
import com.standard.securityCommon.access.RequestAuthorityAttribute;
import com.standard.securityCommon.provider.RequestAuthoritiesService;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
public class RequestAuthoritiesServiceImpl implements RequestAuthoritiesService {


   private OAthGrantedAuthorityService oAthGrantedAuthorityService;
   /**
    * 查询出对应请求的权限设置
    * */
   @Override
   public List<RequestAuthorityAttribute> listAllAttributes(String method) {
       List<OAthGrantedAuthority> list = oAthGrantedAuthorityService.getAuthorityByMethodAndResourceId(method, ConstParam.RESOURCE_ID);
       return list.stream()
                  .map(ele ->new RequestAuthorityAttribute(ele.getApiUri(),
                                                           ele.getMethod(),
                                                           ele.getMactherType(),
                                                           ele.getRoleId(),
                                                           ele.getRoleName(),
                                                           ele.getScope()))
                  .collect(Collectors.toList());
   }
}
