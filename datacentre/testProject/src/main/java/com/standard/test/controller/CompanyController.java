package com.standard.test.controller;

import com.standard.base.componet.dto.ResultDto;
import com.standard.base.controller.BaseController;
import com.standard.test.entity.Company;
import com.standard.test.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("company")
public class CompanyController extends BaseController<Company,Long> {
    @Autowired
    CompanyService companyService;
    @GetMapping("testExtention")
    @ResponseBody
    @ApiOperation(value="testExtention", notes="testExtention")
    public ResultDto testExtention(){
        return ResultDto.success(companyService.testExtention());
    }
}
