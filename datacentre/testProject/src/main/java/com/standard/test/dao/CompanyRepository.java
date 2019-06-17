package com.standard.test.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.test.entity.Company;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends BaseRepository<Company,Long> {
}
