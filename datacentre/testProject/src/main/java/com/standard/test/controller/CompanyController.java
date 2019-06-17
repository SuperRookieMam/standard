package com.standard.test.controller;

import com.standard.base.controller.BaseController;
import com.standard.test.entity.Company;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyController extends BaseController<Company,Long> {
}
