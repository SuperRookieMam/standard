package com.standard.server.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.oauthCommon.dto.ClientDetailsDto;
import com.standard.server.entitiy.OauthClientDetails;
import com.standard.server.service.OauthClientDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OauthClientDetailsServiceImpl extends BaseServiceImpl<OauthClientDetails, String> implements OauthClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetails oauthClientDetails =findById(clientId);
        if (ObjectUtils.isEmpty(oauthClientDetails)){
            throw new ClientRegistrationException("请先注册客户端到资源服务器.......");
        }
        return ClientDetailsDto.toDto(oauthClientDetails);
    }
}
