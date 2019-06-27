package com.standard.permission.service;

import com.standard.base.service.BaseService;
import com.standard.permission.entity.UserRole;

import java.util.List;

public interface UserRoleService extends BaseService<UserRole, Long> {

    List<UserRole> getUserRoleByUsername(String username);
}
