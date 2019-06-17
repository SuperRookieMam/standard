package com.standard.test.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.test.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department,Long> {
}
