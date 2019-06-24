package com.standard.resource.service;

import com.standard.base.service.BaseService;
import com.standard.resource.entitiy.OauthClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

public interface OauthClientDetailsService extends BaseService<OauthClientDetails, String> ,ClientDetailsService{

}
