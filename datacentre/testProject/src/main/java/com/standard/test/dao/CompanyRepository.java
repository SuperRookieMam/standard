package com.standard.test.dao;

import com.standard.base.dao.BaseRepository;
import com.standard.test.entity.Company;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Primary
public interface CompanyRepository extends BaseRepository<Company,Long> {
    List<Company> testExtention();
}
