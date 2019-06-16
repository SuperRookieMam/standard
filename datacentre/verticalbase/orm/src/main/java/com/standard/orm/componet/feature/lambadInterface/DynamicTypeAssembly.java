package com.standard.orm.componet.feature.lambadInterface;

import com.standard.orm.componet.feature.DynamicTypeSelect;

import javax.persistence.criteria.Predicate;

@FunctionalInterface
public interface DynamicTypeAssembly<T>  {
    public Predicate assembly(DynamicTypeSelect<T> dynamicTypeSelect);
}
