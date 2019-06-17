package com.standard.test.service.impl;

import com.standard.base.service.impl.BaseServiceImpl;
import com.standard.test.entity.Department;
import com.standard.test.service.DepartmentService;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<Department,Long> implements DepartmentService {
}
