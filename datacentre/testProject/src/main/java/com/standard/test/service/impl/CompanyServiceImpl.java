package com.standard.test.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.test.entity.Company;
import com.standard.test.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl extends BaseServiceImpl<Company,Long> implements CompanyService {
}
