package com.standard.orm.componet.constant;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

@FunctionalInterface
public interface DynamicAssembly<T>  {

    public Predicate assembly(DynamicPredicateBuilder<T> assembly, FlatBuilder<T> flat, CriteriaQuery<T> query);
}
