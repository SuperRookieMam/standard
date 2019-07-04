package com.standard.permission.controller;

import com.standard.base.controller.BaseController;
import com.standard.permission.entity.RoleInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("roleInfo")
public class RoleInfoController  extends BaseController<RoleInfo,Long> {
}
