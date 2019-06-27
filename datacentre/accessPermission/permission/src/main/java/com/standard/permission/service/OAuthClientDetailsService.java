package com.standard.permission.service;

import com.standard.base.service.BaseService;
import com.standard.permission.entity.OauthClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;

public interface OAuthClientDetailsService extends BaseService<OauthClientDetails, String> , ClientDetailsService {

}
