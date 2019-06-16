package com.standard.orm.componet.feature.lambadInterface;

import com.standard.orm.componet.feature.DynamicDelete;
import com.standard.orm.componet.feature.DynamicPageTupleSelect;

import javax.persistence.criteria.Predicate;

@FunctionalInterface
public interface DynamicPageTupleAssembly<T> {
    public Predicate assembly(DynamicPageTupleSelect<T> dynamicPageTupleSelect);
}
