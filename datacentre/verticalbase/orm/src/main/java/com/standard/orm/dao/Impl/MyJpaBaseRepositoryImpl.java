package com.standard.orm.dao.Impl;

import com.standard.orm.componet.constant.Expression;
import com.standard.orm.dao.MyJpaBaseRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

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
    public Class<T> getDomainClass(){
        return super.getDomainClass();
    }
    public CrudMethodMetadata getRepositoryMethodMetadata() {
        return super.getRepositoryMethodMetadata();
    }

























    protected <S extends T> Page<S> readPage(TypedQuery<S> query, final Class<S> domainClass, Pageable pageable,
                                             @Nullable Specification<S> spec) {

        if (pageable.isPaged()) {
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
        }

        return PageableExecutionUtils.getPage(query.getResultList(), pageable,
                () -> executeCountQuery(getCountQuery(spec, domainClass)));
    }

    protected TypedQuery<T> getQuery(@Nullable Specification<T> spec, Pageable pageable) {

        Sort sort = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
        return getQuery(spec, getDomainClass(), sort);
    }

    protected <S extends T> TypedQuery<S> getQuery(@Nullable Specification<S> spec, Class<S> domainClass,
                                                   Pageable pageable) {

        Sort sort = pageable.isPaged() ? pageable.getSort() : Sort.unsorted();
        return getQuery(spec, domainClass, sort);
    }
    protected TypedQuery<T> getQuery(@Nullable Specification<T> spec, Sort sort) {
        return getQuery(spec, getDomainClass(), sort);
    }
    protected <S extends T> TypedQuery<S> getQuery(@Nullable Specification<S> spec, Class<S> domainClass, Sort sort) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> query = builder.createQuery(domainClass);

        Root<S> root = applySpecificationToCriteria(spec, domainClass, query);
        query.select(root);

        if (sort.isSorted()) {
            query.orderBy(toOrders(sort, root, builder));
        }

        return applyRepositoryMethodMetadata(entityManager.createQuery(query));
    }
    protected <S extends T> TypedQuery<Long> getCountQuery(@Nullable Specification<S> spec, Class<S> domainClass) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);

        Root<S> root = applySpecificationToCriteria(spec, domainClass, query);

        if (query.isDistinct()) {
            query.select(builder.countDistinct(root));
        } else {
            query.select(builder.count(root));
        }

        // Remove all Orders the Specifications might have applied
        query.orderBy(Collections.<Order> emptyList());

        return entityManager.createQuery(query);
    }
    private <S, U extends T> Root<U> applySpecificationToCriteria(@Nullable Specification<U> spec, Class<U> domainClass,
                                                                  CriteriaQuery<S> query) {

        Assert.notNull(domainClass, "Domain class must not be null!");
        Assert.notNull(query, "CriteriaQuery must not be null!");

        Root<U> root = query.from(domainClass);

        if (spec == null) {
            return root;
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        Predicate predicate = spec.toPredicate(root, query, builder);

        if (predicate != null) {
            query.where(predicate);
        }

        return root;
    }
    private <S> TypedQuery<S> applyRepositoryMethodMetadata(TypedQuery<S> query) {
        CrudMethodMetadata metadata = getRepositoryMethodMetadata();
        if (metadata == null) {
            return query;
        }

        LockModeType type = metadata.getLockModeType();
        TypedQuery<S> toReturn = type == null ? query : query.setLockMode(type);

        applyQueryHints(toReturn);

        return toReturn;
    }

    private void applyQueryHints(Query query) {

       /* for (Map.Entry<String, Object> hint : getQueryHints().withFetchGraphs(entityManager)) {
            query.setHint(hint.getKey(), hint.getValue());
        }*/
    }
    private static long executeCountQuery(TypedQuery<Long> query) {

        Assert.notNull(query, "TypedQuery must not be null!");

        List<Long> totals = query.getResultList();
        long total = 0L;

        for (Long element : totals) {
            total += element == null ? 0 : element;
        }

        return total;
    }

    private static boolean isUnpaged(Pageable pageable) {
        return pageable.isUnpaged();
    }


    private static class MyParamsSpecification<T> implements Specification<T> {

        private final Expression expression;

        MyParamsSpecification(Expression expression){
            super();
            this.expression =expression;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            return null;
        }
    }

    private static class MyRunBuildSpecification<T> implements Specification<T> {

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            return null;
        }
    }

}
