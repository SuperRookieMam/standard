package com.standard.admin.controller;

import com.standard.admin.entity.MenuFunction;
import com.standard.admin.entity.MenuFunctionRole;
import com.standard.admin.service.MenuFunctionRoleService;
import com.standard.base.componet.dto.ResultDto;
import com.standard.base.controller.BaseController;
import com.standard.oauthCommon.dto.UserDetailsDto;
import com.standard.permission.componet.annotation.CurrentUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("menuFunctionRole")
public class MenuFunctionRoleController extends BaseController<MenuFunctionRole,Long> {
    @Autowired
    private  MenuFunctionRoleService menuFunctionRoleService;
    @GetMapping("menus")
    @ApiOperation(value="获取登陆用户能访问的菜单", notes="getMenus")
    public ResultDto getMenus(@CurrentUser UserDetailsDto userDetailsDto) {
      List<MenuFunction> list = menuFunctionRoleService.getMenuFunctionByUserName(userDetailsDto.getUsername());
      return ResultDto.success(list);
    }
}
