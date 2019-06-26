package com.standard.server.service;

import com.standard.base.service.BaseService;
import com.standard.server.entity.OAthUserDetailes;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface OAthUserDetailesService extends BaseService<OAthUserDetailes, Long> ,UserDetailsService {

    OAthUserDetailes  findByUsername(String username);
}
