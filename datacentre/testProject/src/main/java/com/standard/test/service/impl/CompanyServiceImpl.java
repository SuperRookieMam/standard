package com.standard.test.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.test.dao.CompanyRepository;
import com.standard.test.entity.Company;
import com.standard.test.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company,Long> implements CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Override
    public List<Company> testExtention() {
        return companyRepository.testExtention();
    }
}
