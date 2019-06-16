package com.standard.orm.componet.feature.lambadInterface;

import com.standard.orm.componet.feature.DynamicUpdate;

import javax.persistence.criteria.Predicate;

@FunctionalInterface
public interface DynamicUpdateAssembly<T> {
    public Predicate assembly(DynamicUpdate<T> DynamicUpdate);
}
