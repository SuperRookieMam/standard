package com.standard.server.service;

import com.standard.base.service.BaseService;
import com.standard.server.entitiy.OauthClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

public interface OauthClientDetailsService extends BaseService<OauthClientDetails, String> ,ClientDetailsService{

}
