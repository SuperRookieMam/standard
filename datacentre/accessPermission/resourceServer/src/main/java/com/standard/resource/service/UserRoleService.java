package com.standard.resource.service;

import com.standard.base.service.BaseService;
import com.standard.resource.entitiy.UserRole;

import java.util.List;

public interface UserRoleService extends BaseService<UserRole, Long> {

    List<UserRole> getUserRoleByUsername(String username);
}
