package com.standard.test.controller;

import com.standard.base.controller.BaseController;
import com.standard.test.entity.Department;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("department")
public class DepartmentController extends BaseController<Department,Long> {
}
