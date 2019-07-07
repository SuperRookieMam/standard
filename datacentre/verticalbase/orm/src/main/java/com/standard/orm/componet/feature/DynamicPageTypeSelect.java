package com.standard.orm.componet.feature;

import com.standard.orm.componet.feature.lambadInterface.DynamicPageTypeAssembly;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DynamicPageTypeSelect<T> implements Serializable {
    private static final long serialVersionUID = 4902747308932587487L;
    public final DynamicPredicateBuilder<T> assembly;
    public final FlatBuilder<T> flat;
    public final Root<T> root;
    public final CriteriaQuery<T> query;
    public final CriteriaBuilder builder;
    public final EntityManager entityManager;
    public final int pageNum;
    public final int pageSize;
    public DynamicPageTypeSelect(Class<T> tClass, EntityManager entityManager,int pageNum,int pageSize){
        this.entityManager =entityManager;
        this.builder =entityManager.getCriteriaBuilder();
        this.query =builder.createQuery(tClass);
        this.root =query.from(tClass);;
        this.assembly =new DynamicPredicateBuilder<>(builder,root);
        this.flat =new FlatBuilder<>(builder,root);
        this.pageNum =pageNum;
        this.pageSize =pageSize;
    }

    public DynamicPageTypeSelect<T> dynamicBuild(DynamicPageTypeAssembly<T> dynamicPageTypeAssembly) {
        dynamicPageTypeAssembly.assembly(this);
        return this;
    }
    public PageInfo<T> getResult(){
         PageInfo<T> pageInfo =new PageInfo<>(entityManager.createQuery(this.query).getResultList(),pageNum,pageSize);
        return pageInfo;
    }
}
