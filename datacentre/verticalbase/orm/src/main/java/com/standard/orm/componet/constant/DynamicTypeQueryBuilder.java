package com.standard.orm.componet.constant;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicTypeQueryBuilder<T,ID extends Serializable> {

    private final Root<T> root;
    private final CriteriaQuery<T> query;
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private final JpaEntityInformation<T, ID> entityInformation;
    private final Map<String, CommonAbstractCriteria> queryMap=new HashMap<>();
    private final Map<CriteriaQuery,List<Predicate>> PredicateMap =new HashMap<>();

    public DynamicTypeQueryBuilder(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager){
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.query =criteriaBuilder.createQuery(this.entityInformation.getJavaType());
        this.root = this.query.from(this.entityInformation.getJavaType());
    }



    protected static class DynamicSpecification<T> implements Specification<T> {

        private static final long serialVersionUID = 1506266334507285947L;

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            return null;
        }
    }
}
