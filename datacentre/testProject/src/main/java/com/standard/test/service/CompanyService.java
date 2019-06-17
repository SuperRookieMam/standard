package com.standard.test.service;

import com.standard.base.service.BaseService;
import com.standard.test.entity.Company;

import java.util.List;

public interface CompanyService extends BaseService<Company,Long> {
    List<Company> testExtention();
}
