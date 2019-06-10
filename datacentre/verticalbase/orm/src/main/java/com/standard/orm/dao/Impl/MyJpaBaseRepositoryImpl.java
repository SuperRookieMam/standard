package com.standard.orm.dao.Impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.standard.orm.componet.constant.Expression;
import com.standard.orm.componet.constant.WhereContext;
import com.standard.orm.componet.util.ParamsPredicateBuilder;
import com.standard.orm.dao.MyJpaBaseRepository;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.jpa.QueryHints;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.CrudMethodMetadata;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

























    protected <S extends T> TypedQuery<S> getQuery(Class<S> domainClass, WhereContext whereContext) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<S> query = builder.createQuery(domainClass);

        //构建查询以及查询条件
        Root<S> root = applySpecificationToCriteria(new MyParamsSpecification(whereContext.getExpressions()), domainClass, query);
        query.select(root);
        //如果需要排序排序
        toSort(whereContext.getSort(),query);
         // 如果需要分组，分组
        groupBy( query, root,whereContext.getGroupby());
        return applyRepositoryMethodMetadata(entityManager.createQuery(query));
    }
    protected <S extends T> TypedQuery<Long> getCountQuery(Class<S> domainClass,WhereContext whereContext) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = builder.createQuery(Long.class);
        //构建查询以及查询条件
        Root<S> root = applySpecificationToCriteria(new MyParamsSpecification(whereContext.getExpressions()), domainClass, query);

        if (query.isDistinct()) {
            query.select(builder.countDistinct(root));
        } else {
            query.select(builder.count(root));
        }

        // 查询条数不需要排序所以传个空的排序进去
        query.orderBy(Collections.<Order> emptyList());
        // 如果需要分组，分组
        groupBy( query, root,whereContext.getGroupby());
        return entityManager.createQuery(query);
    }
    private <S, U extends T> Root<U> applySpecificationToCriteria(@Nullable Specification<U> spec, Class<U> domainClass,CriteriaQuery<S> query) {

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
        for (Map.Entry<String, Object> hint : getQueryHints().withFetchGraphs(entityManager)) {
            query.setHint(hint.getKey(), hint.getValue());
        }
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
    /**
     *获取排序
     * */
    private static void  toSort(JSONArray sort,CriteriaQuery query) {
        List<Sort.Order> list =new ArrayList<>();
        if (!CollectionUtils.isEmpty(sort)){
            for (int i = 0; i < sort.size(); i++) {
                JSONObject jsonObject =sort.getJSONObject(i);
                Sort.Direction direction=  "desc".equalsIgnoreCase(jsonObject.getString("sortType"))
                        ?Sort.Direction.DESC
                        :Sort.Direction.ASC;
                list.add(new Sort.Order(direction,jsonObject.getString("fieldName")));
            }
            query.orderBy(list);
        }
    }

    private   static  void groupBy(CriteriaQuery  query,Root root,JSONArray fieldNames){
        if (!ObjectUtils.isEmpty(fieldNames)){
            List<Path> paths =new ArrayList<>();
            fieldNames.forEach(ele -> {
                String[] panthNames= StringUtils.split(ele.toString(),".");
                Path path=null;
                for (int j = 0; j < panthNames.length; j++) {
                    path =j==0?root.get(panthNames[j]):path.get(panthNames[j]);
                }
                paths.add(path);
            });
            query.groupBy(paths);
        }
    }

    private static class MyParamsSpecification<T> implements Specification<T> {

        private static final long serialVersionUID = 7868312603004690607L;
        private final Iterable<Expression> expressions;

        MyParamsSpecification(Iterable<Expression> expressions){
            super();
            this.expressions =expressions;
        }

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            return ParamsPredicateBuilder.getPredicate(root,criteriaBuilder,expressions);
        }
    }

    private static class MyRunBuildSpecification<T> implements Specification<T> {

        private static final long serialVersionUID = 1506266334507285947L;

        @Override
        public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

            return null;
        }
    }

}
