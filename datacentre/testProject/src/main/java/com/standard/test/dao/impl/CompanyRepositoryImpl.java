package com.standard.test.dao.impl;

import com.standard.orm.dao.Impl.MyJpaBaseRepositoryImpl;
import com.standard.test.dao.CompanyRepository;
import com.standard.test.entity.Company;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.repository.NoRepositoryBean;

import javax.persistence.EntityManager;
import java.util.List;

@NoRepositoryBean
public class CompanyRepositoryImpl extends MyJpaBaseRepositoryImpl<Company,Long> implements CompanyRepository {

    private final JpaEntityInformation<Company, ?> entityInformation;
    private final EntityManager entityManager;

    public CompanyRepositoryImpl(Class<Company> domainClass, EntityManager entityManager){
        super(domainClass,entityManager);
        this.entityManager =entityManager;
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
    }
    @Override
    public List<Company> testExtention(){
        return  findAll();
    }




}
