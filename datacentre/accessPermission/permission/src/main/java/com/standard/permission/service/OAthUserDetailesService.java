package com.standard.permission.service;

import com.standard.base.service.BaseService;
import com.standard.permission.entity.OAthUserDetailes;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OAthUserDetailesService extends BaseService<OAthUserDetailes, Long>, UserDetailsService {
    OAthUserDetailes  findByUsername(String username);
}
