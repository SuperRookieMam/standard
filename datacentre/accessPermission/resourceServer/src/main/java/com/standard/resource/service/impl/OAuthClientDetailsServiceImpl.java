package com.standard.resource.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.resource.cover.ClientDetailsCover;
import com.standard.resource.entity.OauthClientDetails;
import com.standard.resource.service.OAuthClientDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class OAuthClientDetailsServiceImpl extends BaseServiceImpl<OauthClientDetails, String> implements OAuthClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetails oauthClientDetails =findById(clientId);
        if (ObjectUtils.isEmpty(oauthClientDetails)){
            throw new ClientRegistrationException("请先注册客户端到资源服务器.......");
        }
        return ClientDetailsCover.toDto(oauthClientDetails);
    }
}
