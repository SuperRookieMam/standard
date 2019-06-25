package com.standard.resource.componet.feature;

import com.standard.resource.componet.constpackage.ConstParam;
import com.standard.resource.entitiy.OAthGrantedAuthority;
import com.standard.resource.service.OAthGrantedAuthorityService;
import com.standard.securityCommon.access.RequestAuthorityAttribute;
import com.standard.securityCommon.provider.RequestAuthoritiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RequestAuthoritiesServiceImpl implements RequestAuthoritiesService {

    @Autowired
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
