package com.standard.server.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.server.entitiy.OAuthClientDetails;
import com.standard.server.service.OAuthClientDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OAuthClientDetailsServiceImpl extends BaseServiceImpl<OAuthClientDetails,String>implements OAuthClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OAuthClientDetails clientDetails = findById(clientId);
        if (ObjectUtils.isEmpty(clientDetails)){
            throw new ClientRegistrationException("客户端不存在");
        }
        return clientDetails;
    }
}
