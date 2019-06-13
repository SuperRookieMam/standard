package com.standard.orm.componet.constant;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public class DynamicTypeQueryBuilder<T,ID extends Serializable> implements Serializable{
    private static final long serialVersionUID = -5614365270480188365L;
    private final Root<T> root;
    private final CriteriaQuery<T> query;
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final JpaEntityInformation<T, ID> entityInformation;

    public DynamicTypeQueryBuilder(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager){
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.query =criteriaBuilder.createQuery(this.entityInformation.getJavaType());
        this.root = this.query.from(this.entityInformation.getJavaType());
    }
    public CriteriaQuery query(){
        return this.query;
    }
}
