package com.standard.server.service;

import com.standard.base.service.BaseService;
import com.standard.server.entity.OAuthClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

public interface OAuthClientDetailsService extends BaseService<OAuthClientDetails, String> ,ClientDetailsService{

}
