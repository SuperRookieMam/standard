package com.standard.admin.service;

import com.standard.admin.entity.MenuFunction;
import com.standard.base.service.BaseService;
import com.standard.admin.entity.MenuFunctionRole;

import java.util.List;

public interface MenuFunctionRoleService extends BaseService<MenuFunctionRole, Long> {

    List<MenuFunction> getMenuFunctionByUserName(String userName);
}
