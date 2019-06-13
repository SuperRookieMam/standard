package com.standard.orm.componet.constant;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class DynamicSpecification<T> {
    private static final long serialVersionUID = -6274355106389230973L;
    public final DynamicPredicateBuilder<T> assembly;
    public final FlatBuilder<T> flat;
    public final Root<T> root;
    public final CriteriaQuery<T> query;
    public final CriteriaBuilder criteriaBuilder;
    public final List<Predicate> list ;
    public DynamicSpecification(Root<T> root,CriteriaQuery<T> query,CriteriaBuilder criteriaBuilder){
        this.root =root;
        this.criteriaBuilder =criteriaBuilder;
        this.query =query;
        this.assembly =new DynamicPredicateBuilder<>(criteriaBuilder,root);
        this.flat =new FlatBuilder<>(criteriaBuilder,root);
        this.list = new ArrayList<>();
    }

    public Predicate toPredicate(DynamicAssembly<T> dynamicAssembly) {
        return dynamicAssembly.assembly(assembly,flat,query);
    }
}
