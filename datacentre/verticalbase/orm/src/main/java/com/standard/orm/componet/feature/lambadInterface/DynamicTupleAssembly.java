package com.standard.orm.componet.feature.lambadInterface;

import com.standard.orm.componet.feature.DynamicTupleSelect;

import javax.persistence.criteria.Predicate;

@FunctionalInterface
public interface DynamicTupleAssembly<T> {
    public Predicate assembly(DynamicTupleSelect<T> dynamicTupleSelect);
}
