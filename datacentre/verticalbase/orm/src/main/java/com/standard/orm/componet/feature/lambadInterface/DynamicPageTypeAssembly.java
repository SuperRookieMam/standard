package com.standard.orm.componet.feature.lambadInterface;

import com.standard.orm.componet.feature.DynamicPageTypeSelect;

import javax.persistence.criteria.Predicate;

@FunctionalInterface
public interface DynamicPageTypeAssembly<T> {
    public Predicate assembly(DynamicPageTypeSelect<T> dynamicPageTypeSelect);
}
