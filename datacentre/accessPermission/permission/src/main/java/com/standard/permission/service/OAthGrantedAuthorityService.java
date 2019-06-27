package com.standard.permission.service;

import com.standard.base.service.BaseService;
import com.standard.permission.entity.OAthGrantedAuthority;

import java.util.List;

public interface OAthGrantedAuthorityService extends BaseService<OAthGrantedAuthority, Long> {

    List<OAthGrantedAuthority> getAuthorityByMethodAndResourceId(String method, String resourceId);
}
