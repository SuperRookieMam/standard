package com.standard.orm.componet.constant;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicQueryBuilder<T,ID extends Serializable> {
    // 实体包含实体的ID信息
    private final JpaEntityInformation<T, ID> entityInformation;
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<T> query;
    private final Map<CriteriaQuery,List<Predicate>> map =new HashMap<>();
    private final Root<T> root;

    public  DynamicQueryBuilder (JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager){
        this.entityInformation = entityInformation;
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
        this.query =criteriaBuilder.createQuery(this.entityInformation.getJavaType());
        this.root = this.query.from(this.entityInformation.getJavaType());
    }
    /*如果只需要反回固定的几个字段的话，可以在这里添加需要的字段*/
    public DynamicQueryBuilder<T,ID> select(String ...select){
        return this;
    }



    protected static class DynamicSpecification<T> implements Specification<T> {

        private static final long serialVersionUID = 1506266334507285947L;

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            return null;
        }
    }
}
