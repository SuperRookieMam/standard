package com.standard.admin.controller;

import com.standard.base.controller.BaseController;
import com.standard.admin.entity.MenuFunction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("menuFunction")
public class MenuFunctionController extends BaseController<MenuFunction,Long> {

}
