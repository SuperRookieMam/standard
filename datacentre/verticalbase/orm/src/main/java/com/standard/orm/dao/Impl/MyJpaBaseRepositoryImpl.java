package com.standard.orm.dao.Impl;

import com.standard.orm.dao.MyJpaBaseRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;

@Getter
@Setter
public class MyJpaBaseRepositoryImpl<T,ID extends Serializable> extends SimpleJpaRepository<T, ID>implements MyJpaBaseRepository<T,ID> {

    private final JpaEntityInformation<T, ?> entityInformation;
    private final EntityManager entityManager;

    public MyJpaBaseRepositoryImpl(Class<T> domainClass, EntityManager entityManager){
        super(domainClass,entityManager);
        this.entityManager =entityManager;
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
    }

}
