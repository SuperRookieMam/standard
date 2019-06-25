package com.standard.oauthCommon.entity;

import org.springframework.http.HttpMethod;
import org.springframework.security.core.GrantedAuthority;

public interface MGrantedAuthority extends GrantedAuthority {

     Long getId();

     String getResourceId();

     String getApiName();

     String getApiDescription();

     String getApiUri();

     HttpMethod getMethod();

     String getMactherType();

     String getRoleId();

     String getRoleName();

     String getScope();
}
